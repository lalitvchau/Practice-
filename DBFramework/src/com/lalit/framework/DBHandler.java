package com.lalit.framework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public abstract class DBHandler {
	HashMap<String, String> classMap = new HashMap<String, String>();
	HashMap<String, Object> valueMap = new HashMap<String, Object>();
	HashMap<String, Object> conditionMap = new HashMap<String, Object>();
	private String tableName = null;

	// Intilize Table Name same as Class Name of Sub Class of This Class
	private void setTableName(String tableName) {
		this.tableName = tableName;
	}

	private String getTableName() {
		Class<? extends DBHandler> tempClass = this.getClass();
		return tempClass.getSimpleName();
	}

	// Load the all Field name and their type into classMap
	private void mapLoad() {
		Class<? extends DBHandler> tempClass = this.getClass();
		setTableName(tempClass.getSimpleName());
		Field[] fields = tempClass.getDeclaredFields();
		for (Field fieldItretor : fields) {
			this.classMap.put(fieldItretor.getName(), fieldItretor.getType()
					.toString());

		}
		tempClass = null;

	}

	private void mapConditionLoad(String condition) {
		@SuppressWarnings("rawtypes")
		Class cls = this.getClass();
		Object obj = null;
		try {
			obj = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			Method[] methods = cls.getDeclaredMethods();
			for (Field fieldItretor : fields) {
				String feild = fieldItretor.getName();
				if (feild.startsWith("_")) {
					feild = feild.substring(1);
				}
				feild = " " + feild.toLowerCase();
				condition = " " + condition.toLowerCase();
				if (condition.contains(feild)) {

					for (Method methodItr : methods) {
						String match = methodItr.getName();
						if (match.startsWith("get")) {
							match = match.substring(3);
							if (match.startsWith("_")) {
								match = match.substring(1).toLowerCase();
							}
							match = " " + match;
							if (feild.equals(match.toLowerCase())) {
								try {
									this.conditionMap.put(feild.toLowerCase(),
											methodItr.invoke(obj));
								} catch (Exception e) {
								}
							}
						}
					}
				}
			}

		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

	}

	// Load all field name and their values itnto valueMap
	private void mapValueLoad() {
		Class<? extends DBHandler> tempClass = this.getClass();
		Object obj = null;
		
		try {
			obj = tempClass.newInstance();

		} catch (Exception e1) {

		}
		Method[] methods = tempClass.getDeclaredMethods();
		Field[] fields = tempClass.getDeclaredFields();
		

		for (Field fieldItretor : fields) {
			this.valueMap.put(fieldItretor.getName().toLowerCase(), "markup");
		}
		@SuppressWarnings("unused")
		int i = 0;

		for (Method methodItr : methods) {
			String match = methodItr.getName();
			String feild = match.toLowerCase();
			if (match.startsWith("get")) {
				feild = feild.substring(3);
				try {
					this.valueMap.put(feild, methodItr.invoke(obj));
				} catch (Exception e) {

				}
				i++;
			}

		}
		tempClass = null;
		obj = null;

	}

	// Method for Save data into a existion or new table.. new table
	// autometically genrate
	public String save() {
		String result = null;
		this.mapLoad();
		this.mapValueLoad();
		result = this.insertData().toLowerCase();
		if (result.endsWith("doesn't exist")) {
			result = this.createTable();
			result = this.insertData().toLowerCase();
		}
		if (result.startsWith("duplicate entry")) {
			result = this.updateData();
		}

		return result;
	}

	// Update data into existing table
	private String updateData() {
		String result = null;
		result = "Updationg";
		Connection con = null;
		PreparedStatement pstmt = null;
		int resultUpdate;
		String sql = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/mydatabase?user=j2ee&password=j2ee");
			con.setAutoCommit(false);
			sql = "update " + tableName + "  set ";
			Iterator<String> itr = this.classMap.keySet().iterator();
			itr = this.classMap.keySet().iterator();
			int i = 0;
			String item = null;
			String item1 = null;
			while (itr.hasNext()) {
				String temp = itr.next();
				if (!temp.startsWith("_")) {

					temp = temp.toLowerCase();
					sql += temp + " =\"" + this.valueMap.get(temp) + "\"";
					if (itr.hasNext()) {
						sql += ", ";
					}
				} else {
					item1 = temp.toLowerCase();
					item = temp.substring(1);
					item = item.toLowerCase();

				}
			}
			sql += "where " + item + " = " + this.valueMap.get(item1) + ";";
			sql=sql.replace(", where", " where");
			pstmt = con.prepareStatement(sql);
			resultUpdate = pstmt.executeUpdate();
			con.commit();
			if (resultUpdate >= 0) {
				result = "Data Update";
			}

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				result = e.getMessage();
			}
			result = e.getMessage();
		}
		try {
			if (con != null) {
				con.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			result = e.getMessage();
		}
		return result;
	}

	// Insert Data into a table
	private String insertData() {
		String result = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		int resultUpdate;
		String sql = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/mydatabase?user=j2ee&password=j2ee");
			con.setAutoCommit(false);
			sql = "insert into " + tableName + "( ";
			Iterator<String> itr = this.classMap.keySet().iterator();
			while (itr.hasNext()) {
				String item = itr.next();
				if (item.startsWith("_")) {
					item = item.substring(1);
				}
				sql += " " + item;
				if (itr.hasNext()) {
					sql += ", ";
				}
			}
			sql += " ) values( ";
			itr = this.classMap.keySet().iterator();
			while (itr.hasNext()) {

				String temp = itr.next().toLowerCase();
				sql += " \"" + this.valueMap.get(temp) + "\"";
				if (itr.hasNext()) {
					sql += ", ";
				}
			}
			sql += " );";
			pstmt = con.prepareStatement(sql);
			resultUpdate = pstmt.executeUpdate();
			con.commit();
			if (resultUpdate >= 0) {
				result = "Data Inserted";
			}

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				result = e.getMessage();
			}
			result = e.getMessage();
		}
		try {
			if (con != null) {
				con.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			result = e.getMessage();
		}
		return result;
	}

	// Create a new class using classMap
	private String createTable() {
		String result = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		int resultUpdate;
		String sql = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/mydatabase?user=j2ee&password=j2ee");
			sql = "create table " + tableName + "( ";
			Iterator<String> itr = this.classMap.keySet().iterator();
			boolean prime = false;
			String item = null;
			String item1 = null;
			String primaryKey = null;
			while (itr.hasNext()) {
				item = itr.next();
				if (item.startsWith("_")) {
					item1 = item.substring(1);
					prime = true;
				} else {
					item1 = item;
				}
				sql += " " + item1 + " ";

				if (this.classMap.get(item).equals("class java.lang.String")) {
					sql += " varchar(100) ";
				} else if (this.classMap.get(item).equals("short")
						|| this.classMap.get(item).equals("byte")) {
					sql += " int ";
				} else {
					sql += " " + this.classMap.get(item) + "  ";
				}

				if (prime) {
					sql += " not null";
					primaryKey = item1;
					prime = false;
				}
				sql += ", ";

			}
			sql += "primary key(" + primaryKey + ") );";

			pstmt = con.prepareStatement(sql);
			resultUpdate = pstmt.executeUpdate();
			if (resultUpdate >= 0) {
				result = "Table Created";
			}
		} catch (Exception e) {
			result = e.getMessage();

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result = e.getMessage();
			}

		}

		return result;
	}

	// return all data from the table
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> getData() {
		String qry1 = " * ";
		String qry2 = getTableName();
		return selectQuery(qry1, qry2, ";");
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> getData(String condition) {
		String qry1 = " * ";
		String qry2 = getTableName();

		condition = " " + condition;
		condition = condition.replace(" _", " ");
		return selectQuery(qry1, qry2, condition);
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> getData(String columnsNames, String condition) {
		if (columnsNames.equals("") || columnsNames == null) {
			columnsNames = " * ";
		}
		columnsNames = " " + columnsNames;
		columnsNames = columnsNames.replace(" _", " ");
		String qry2 = getTableName();
		condition = " " + condition;
		condition = condition.replace(" _", " ");
		return selectQuery(columnsNames, qry2, condition);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<HashMap> getData(String columnsNames, String condition,
			Object... values) {

		ArrayList<HashMap> list = new ArrayList<HashMap>();
		if (columnsNames.equals("") || columnsNames == null) {
			columnsNames = " * ";
		}
		int dynamicValues = checkDynamicValues(condition);
		int lengthValues = values.length;
		if (dynamicValues == 0) {
			lengthValues = 0;
			condition = ";";
		}

		if (dynamicValues == lengthValues) {
			columnsNames = " " + columnsNames;
			columnsNames = columnsNames.replace(" _", " ");
			condition = " " + condition;
			condition = condition.replace(" _", " ");
			String qry1 = managerQry1(columnsNames);
			String qry2 = getTableName();
			String qry = mergeQry(qry1, qry2, condition);

			ResultSet rs = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSetMetaData rsm = null;

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/mydatabase?user=j2ee&password=j2ee");

				if (dynamicValues == 0) {
					pstmt = con.prepareStatement(qry.toLowerCase());

				} else if (dynamicValues > 0) {
					pstmt = con.prepareStatement(qry.toLowerCase());
					int i = 1;
					for (Object value : values) {
						if (dynamicValues >= i) {
							pstmt.setObject(i++, value);
						}
					}
				}
				rs = pstmt.executeQuery();
				rsm = rs.getMetaData();

				while (rs.next()) {
					int i = 1;
					HashMap map = new HashMap();
					boolean fleg = true;
					while (fleg == true) {
						try {
							String columnName = rsm.getColumnName(i);
							String columnType = rsm.getColumnTypeName(i++)
									.toLowerCase();
							if (columnType.equals("varchar")) {
								map.put(columnName, rs.getString(columnName));
							} else if (columnType.equals("int")
									|| columnType.equals("byte")
									|| columnType.equals("short")) {
								map.put(columnName, rs.getInt(columnName));
							} else if (columnType.equals("float")) {
								map.put(columnName, rs.getFloat(columnName));
							} else if (columnType.equals("long")) {
								map.put(columnName, rs.getLong(columnName));
							} else if (columnType.equals("double")) {
								map.put(columnName, rs.getDouble(columnName));
							} else if (columnType.equals("boolean")) {
								map.put(columnName, rs.getBoolean(columnName));
							} else if (columnType.equals("blob")) {
								map.put(columnName, rs.getBlob(columnName));
							} else if (columnType.equals("clob")) {
								map.put(columnName, rs.getClob(columnName));
							} else {
								map.put(columnName, rs.getObject(columnName));
							}
						} catch (Exception e) {
							fleg = false;
						}
					}
					list.add(map);
				}

			} catch (Exception e) {
			} finally {
				try {
					if (con != null) {
						con.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {

				}

			}

			return list;
		} else {
			list.add((HashMap) new HashMap().put(
					"no of ? marks and values are difrent",
					"no of ? marks and values are difrent"));
			return list;
		}
	}

	// This manage the querry part first like select * from
	private String managerQry1(String qry1) {
		qry1 = "select " + qry1 + " from ";
		return qry1;
	}

	// This method all querry into single querry String
	private String mergeQry(String qry1, String qry2, String condition) {
		if (condition.equals(";") || condition.contains("")
				|| condition.contains(";") || condition == null) {
			condition = " ;";
			return qry1 + qry2 + condition;
		} else {
			return qry1 + qry2 + " where  " + condition + ";";
		}
	}

	// This method count ? in qurry
	private int checkDynamicValues(String qry) {
		int counter = 0;
		int length = qry.length();
		for (int i = 0; i < length; i++) {
			if (qry.charAt(i) == '?') {
				counter++;
			}
		}

		return counter;
	}

	// perform select operation on querry
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<HashMap> selectQuery(String qry1, String qry2,
			String condition) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSetMetaData rsm = null;
		ArrayList<HashMap> list = new ArrayList<HashMap>();
		try {
			qry1 = managerQry1(qry1);
			String qry = mergeQry(qry1, qry2, condition);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/mydatabase?user=j2ee&password=j2ee");

			int dynamicValues = checkDynamicValues(qry);
			if (dynamicValues == 0) {
				pstmt = con.prepareStatement(qry.toLowerCase());

			} else if (dynamicValues > 0) {
				pstmt = con.prepareStatement(qry.toLowerCase());
				this.mapConditionLoad(condition);
				String[] subString = condition.split(" ");

				int i = 1;
				for (String matchStr : subString) {
					Iterator itr = conditionMap.keySet().iterator();
					while (itr.hasNext()) {

						String field = (String) itr.next();
						matchStr = " " + matchStr;
						if (matchStr.contains(field)) {
							pstmt.setObject(i++, conditionMap.get(field));
						}
					}

				}

			}
			rs = pstmt.executeQuery();
			rsm = rs.getMetaData();

			while (rs.next()) {
				int i = 1;
				HashMap map = new HashMap();
				boolean fleg = true;
				while (fleg == true) {
					try {
						String columnName = rsm.getColumnName(i);
						String columnType = rsm.getColumnTypeName(i++)
								.toLowerCase();
						if (columnType.equals("varchar")) {
							map.put(columnName, rs.getString(columnName));
						} else if (columnType.equals("int")
								|| columnType.equals("byte")
								|| columnType.equals("short")) {
							map.put(columnName, rs.getInt(columnName));
						} else if (columnType.equals("float")) {
							map.put(columnName, rs.getFloat(columnName));
						} else if (columnType.equals("long")) {
							map.put(columnName, rs.getLong(columnName));
						} else if (columnType.equals("double")) {
							map.put(columnName, rs.getDouble(columnName));
						} else if (columnType.equals("boolean")) {
							map.put(columnName, rs.getBoolean(columnName));
						} else if (columnType.equals("blob")) {
							map.put(columnName, rs.getBlob(columnName));
						} else if (columnType.equals("clob")) {
							map.put(columnName, rs.getClob(columnName));
						} else {
							map.put(columnName, rs.getObject(columnName));
						}
					} catch (Exception e) {
						fleg = false;
					}
				}
				list.add(map);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

		}

		return list;
	}

}
