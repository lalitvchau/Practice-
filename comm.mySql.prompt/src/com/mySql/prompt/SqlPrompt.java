package com.mySql.prompt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Driver;

public class SqlPrompt {
   public static void main(String[] args) {
	   Connection con=null;
	   Scanner sc =new Scanner(System.in);
	  try {
		Driver dvr=new Driver();
		DriverManager.registerDriver(dvr);
		String url=null;
		String userName=null;
		String passWord=null;
		boolean checkId=true;
		System.out.println("*********************** SQL Prompt *****************************");
		while(checkId){
		  
		  System.out.print("\nEnter User Name = ");
		  userName=sc.next();
		  System.out.print("Enter "+userName+" Password = ");
		  passWord=sc.next();
		  
		  try {
			  url="jdbc:mysql://localhost:3306/test";
			  con=DriverManager.getConnection(url,userName,passWord);
			  checkId=false;
		    } catch (SQLException e) {
		    	
			System.err.println("User or Password does not exist in Database");
			checkId=true;
		    }
		}//while(checkId) Closed
		  
		  System.out.println("Succefully Login\nWELCOME USER "+userName+">>\nAre you want chose your own Database than press yes\n otherwise press any word and work under default MYSQL.test Databse\n"+userName+">>");
	      String isYes=sc.next();
		  isYes=isYes.toLowerCase();
		  
         if(isYes.equals("yes")){
        	  con.close();
        	  checkId=true;
        	  while(checkId){
        		  System.out.println("Enter Your Datbase Name");
        		  String dbName=sc.next();
        		  
        		  url="jdbc:mysql://localhost:3306/"+dbName;
        		  
        		   
        		       try {
						     con=DriverManager.getConnection(url,userName,passWord);
						     checkId=false;
						     System.out.println("You work on "+dbName+" database");
						     QueriesChecker.queries(con);
					       } catch (Exception e) {
					    	      System.err.println("Database doesn't exist ! try Again");
					    }//try catch close
        	  }//while close
         }else{
        	  checkId=true;
        	  
        	       System.out.println("You work on TEST database");
        	       QueriesChecker.queries(con);
        	       
         }//else close
		
	} catch (SQLException  e) {
		e.printStackTrace();
	}//catch close
	  finally{
		  try {
			  if(con!=null){
			            con.close();
			            }
			  if(sc!=null){
		            sc.close();
		            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
 }//end main method
}//end class
