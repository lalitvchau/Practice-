package com.j2ee.assigenment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class UserPasswordVerification {
    public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Properties info=null;
		FileInputStream inStream=null;
		Scanner sc=new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url="jdbc:mysql://localhost:3306/mydatabase";
			inStream=new FileInputStream("unpass.properties");
			info=new Properties();
			info.load(inStream);
			con=DriverManager.getConnection(url, info);
			System.out.println("Enter regno :");
			int regno=sc.nextInt();
			System.out.println("Enter password");
			String pass=sc.next();
			ps=con.prepareStatement("select * from student_other_info where regno=? and password=?");
			ps.setInt(1,regno);
			ps.setString(2, pass);
			rs=ps.executeQuery();
			if(rs.next()){
				System.out.println("Regno and Password is Vaild !");
			}else{
				System.err.println("Regno and password inVaild !");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(con!=null){
					con.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
				if(sc!=null){
					sc.close();
				}
				if(inStream!=null){
					inStream.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
