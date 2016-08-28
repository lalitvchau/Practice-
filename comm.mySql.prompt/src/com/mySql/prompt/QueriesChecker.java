package com.mySql.prompt;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class QueriesChecker {
   public static void queries(Connection con) {
	    String qry=null;
	    Statement stmt=null;
	    ResultSet rs=null;
	    FileInputStream stream=null;
	    Scanner sc=new Scanner(System.in);
	    
	    try {
		stmt=con.createStatement();
		String cmd="start";	
		System.out.println("SQL Queries");
		System.out.println("********************************************************************");
		System.out.print("SQL>> ");
		 while(!cmd.equals("exit")){
			boolean fleg=true;
			boolean isDbResult = false;
			while(fleg){
				try{
					qry=sc.nextLine();
					if(qry.equals("exit")){
						break;
					}else{
					isDbResult=stmt.execute(qry);
					fleg=false;}
				}catch(SQLException e){
					fleg=true;
					System.err.println(e.getMessage());
					System.out.println("Enter a Vaild Query\nSQL>> ");
					
				}
			}//inner while close
			if(isDbResult){
				rs=stmt.getResultSet();
				
				ResultSetMetaData rsm=rs.getMetaData();
				System.out.println("\n-------------------------------------------------------------------------------------------------");
				int k=1;
				boolean fleg1=true;
				   if(rs.next()){
				     while(fleg1){
					           try{							 
				              System.out.print(rsm.getColumnLabel(k++)+"\t     ");
				                   }
						        catch(Exception e){
							                       fleg1=false;}
						      }
				   }else{
					   System.err.print("No rows Founded");
				   }
					 System.out.println("\n--------------------------------------------------------------------------------------------------------");
					 int rowCount=0;
					 rs.first();
					 rs.previous();
			  while(rs.next()){
				  int row=1;
					boolean fl=true;
					 while(fl){
						 try{
							 
				              System.out.print(rs.getString(row++)+"\t\t ");
				         }
						 catch(Exception e){
							 fl=false;}
						 }
					 System.out.println();
					 rowCount++;;
					 }
			  System.out.println("\n---------------------------------------------------------------------");
				System.out.println("RESULT>> "+rowCount+" rows displayed");
			}
			else{
				int count=stmt.getUpdateCount();
				if(count<0){
					break;
				}else{
				System.out.println(count+" row affected !");}
			}
			System.out.print("SQL>>");
		 }
			
		} catch (SQLException  e) {
			e.printStackTrace();
		}
	    finally{
			try {
				if(stream!=null){
					stream.close();}
				if(stream!=null){
					stream.close();}
				if(rs!=null){
					rs.close();}
				if(stmt!=null){
					stmt.close();}
				if(con!=null){
					con.close();}
				System.out.println("Good Bye !");
				System.exit(0);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	    
	    
}

}
