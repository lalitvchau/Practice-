package com.j2ee.assigenment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Procedure1 {
    public static void main(String[] args) {
		Connection con=null;
		CallableStatement cstmt=null;
		Properties ps=null;
		FileInputStream inStream=null;
		Scanner sc=new Scanner(System.in);
		int isDbResult=0;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url="jdbc:mysql://localhost:3306/mydatabase";
			ps= new Properties();
			inStream=new FileInputStream("unpass.properties");
			ps.load(inStream);
			
			con=DriverManager.getConnection(url, ps);
			cstmt=con.prepareCall("call studentUpSert(?,?,?,?,?,?,?,?,?)");
			System.out.println("Enter Registration Number =");
			int regno=sc.nextInt();
			System.out.println("Enter Student First Name =");
			String sc1=sc.nextLine();
			cstmt.setInt(1,regno);
			cstmt.setString(2,sc1);
			System.out.println("Enter Student Middle Name =");
			cstmt.setString(3,sc.nextLine());
			System.out.println("Enter Student Last Name =");
			cstmt.setString(4,sc.nextLine());
			System.out.println("Enter Father First Name =");
			cstmt.setString(5,sc.nextLine());
			System.out.println("Enter Father Middle Name =");
			cstmt.setString(6,sc.nextLine());
			System.out.println("Enter Father Last Name =");
			cstmt.setString(7,sc.nextLine());
			System.out.println("Password =");
			cstmt.setString(9,sc.nextLine());
			System.out.println("Is Admin=");
			cstmt.setString(8,sc.next());
			isDbResult=cstmt.executeUpdate();
			System.out.println("No of row Affected ="+isDbResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(con!=null){
					con.close();
				}
				if(cstmt!=null){
					cstmt.close();
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
