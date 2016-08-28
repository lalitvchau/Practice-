package com.lalit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lalit.beans.Education;
import com.lalit.beans.Person;
import com.lalit.util.DbUtil;

public class PersonDao {
	public static List<Education> getAllEducations(Connection con) {
		List<Education> list1 = new ArrayList<Education>();
		String sql = "select * from educations";
		Statement stmt = null;
		ResultSet rs = null;
		Education edu = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				edu = new Education();
				edu.setDesc(rs.getString("e_desc"));
				edu.setId(rs.getInt("id"));
				edu.setName(rs.getString("name"));
				list1.add(edu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closeStatement(stmt);
		}
		return list1;
	}

	public static int insertPerson(Connection con, int id, String name,
			int age, int eduId) {
		int status = 0;
		String sql = "insert into persons(name, id, age, edu_id) values(?,?,?,?)";
		PreparedStatement ps = null;

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setInt(2, id);
			ps.setInt(4, eduId);
			ps.setString(1, name);
			ps.setInt(3, age);
			status = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {

					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public static List<Person> getSearch(Connection con, String stringColumnName,
			String columnValue) {
		List<Person> personList = new ArrayList<Person>();
		StringBuffer sql = new StringBuffer("select * from persons where ");
		if ("id".equals(stringColumnName) || "age".equals(stringColumnName)) {
			sql.append(" " + stringColumnName + " =" + columnValue);
		}
		if ("eduId".equals(stringColumnName)) {
			sql.append(" " + stringColumnName
					+ " = select id from educations where name='" + columnValue
					+ "'");
		}
		if ("name".equals(stringColumnName)) {
			sql.append(" " + stringColumnName + " like'%" + columnValue + "%'");
		}
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql.toString());
			Person person = new Person();
			while (rs.next()) {
				person.setAge(rs.getInt("age"));
				person.setId(rs.getInt("id"));
				person.setEducation(rs.getInt("edu_id"));
				person.setName(rs.getString("name"));
				personList.add(person);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closeStatement(st);
		}
		return personList;

	}
	
	
	public static Person readPerson(Connection con, int id) {
		StringBuffer sql = new StringBuffer("select * from persons where id="+id);
		
		Statement st = null;
		ResultSet rs = null;
		Person person = new Person();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql.toString());
			
			while (rs.next()) {
				person.setAge(rs.getInt("age"));
				person.setId(rs.getInt("id"));
				person.setEducation(rs.getInt("edu_id"));
				person.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closeStatement(st);
		}
		return person;

	}
	public static String getEducation(Connection con, int id) {
		StringBuffer sql = new StringBuffer("select * from educations where id="+id);
		
		Statement st = null;
		ResultSet rs = null;
		String person=null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql.toString());
			rs.next();
				person=rs.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closeStatement(st);
		}
		return person;

	}
	public static int savePerson(Connection con, int id, String name,
			int age, int eduId) {
		int status = 0;
		String sql = "update persons set name =?, age= ?, edu_id= ? where id= ?";
		PreparedStatement ps = null;

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setInt(3, eduId);
			ps.setInt(4, id);
			ps.setString(1, name);
			ps.setInt(2, age);
			status = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {

					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	public static int deletePerson(Connection con, int id) {
		int status = 0;
		String sql = "delete from  persons where id= ?";
		PreparedStatement ps = null;

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			
			ps.setInt(1 ,id);
			
			status = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {

					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}


}
