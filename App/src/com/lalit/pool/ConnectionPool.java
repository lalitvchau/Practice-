package com.lalit.pool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Connection;
public class ConnectionPool {
	private static Vector<Connection> pool = new Vector<Connection>();
	private String driverClass = null;
	private String dbUrl = null;
	private String userName = null;
	private String password = null;
	private int poolSize = 0;

	public ConnectionPool() {
		super();
		this.driverClass = "com.mysql.jdbc.Driver";
		this.dbUrl = "jdbc:mysql://localhost:3306/student_data";
		this.userName = "j2ee";
		this.password = "j2ee";
		this.poolSize = 10;
		init();
	}

	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, userName, password);
	}

	private void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	private void init() {
		try {
			Class.forName(driverClass);
			Connection con = null;
			for (int i = 0; i < poolSize; i++) {
				con = openConnection();
				pool.add(con);
			}

		} catch (ClassNotFoundException | SQLException e) {
		}
	}
	public void realse() {
		try {
			for (int i = 0; i < pool.size(); i++) {
				
				closeConnection(pool.remove(0));
			}

		} catch (SQLException e) {
		}
	}

	public Connection checkOut() {
		Connection con = null;
		if (pool.size() > 0) {
			con = pool.remove(0);

		} else {
			try {
				con = openConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return con;

	}

	public void checkIn(Connection con) {
		if (con != null) {
			if (pool.size() >= poolSize) {
				try {
					closeConnection(con);
					con=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				pool.add(con);
				con=null;
			}

		}
	}
}
