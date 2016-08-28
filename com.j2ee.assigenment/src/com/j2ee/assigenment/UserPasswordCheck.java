package com.j2ee.assigenment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public  class UserPasswordCheck {
	   public static void main(String[] args) {
		   isVaild(7,"abc123");
	    }
       public static String isVaild(int regno, String password){
		   
        	Connection con=null;
    		PreparedStatement ps=null;
    		ResultSet rs=null;
    		Properties info=null;
    		FileInputStream inStream=null;
    		String result=null;
    		try {
    			Class.forName("com.mysql.jdbc.Driver").newInstance();
    			String url="jdbc:mysql://localhost:3306/mydatabase";
    			inStream=new FileInputStream("unpass.properties");
    			info=new Properties();
    			info.load(inStream);
    			con=DriverManager.getConnection(url, info);
    			ps=con.prepareStatement("select * from student_other_info where regno=? and password=?");
    			ps.setInt(1,regno);
    			ps.setString(2, password);
    			rs=ps.executeQuery();
    			if(rs.next()){
    				result="Regno and Password is Vaild !";
    			}else{
    				  result="Regno and password inVaild !";
    			}
    			
    		} catch (Exception e) {
    		    result=e.getMessage();
    			
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
    				if(inStream!=null){
    					inStream.close();
    				}
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				result=e.getMessage();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				result=e.getMessage();
    			}
    		}
    	
        	return result;
        }
}
