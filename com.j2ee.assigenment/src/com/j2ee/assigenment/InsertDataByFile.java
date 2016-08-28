package com.j2ee.assigenment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.CallableStatement;
public class InsertDataByFile {
  public static void main(String[] args) {
	FileReader freader =null;
	BufferedReader bf=null;
	Connection con=null;
	FileInputStream stream=null;
	CallableStatement cstmt=null;
	ResultSet rs=null;
	Statement st=null;
	FileWriter fo=null;
	BufferedWriter bfw=null;
	int count=0;
	String dataLine=null;
	 try {
		freader=new FileReader("file/Data.txt");
		fo=new FileWriter("file/OutPut.txt");
		stream=new FileInputStream("unpass.properties");
		bf=new BufferedReader(freader);
		bfw=new BufferedWriter(fo);
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String dbUrl="jdbc:mysql://localhost:3306/mydatabase";
		Properties info=new Properties();
		info.load(stream);
		con=DriverManager.getConnection(dbUrl,info);
		dataLine=bf.readLine();
		cstmt=con.prepareCall("call studentUpSert(?,?,?,?,?,?,?,?,?)");
		while(dataLine!=null)
		{
			String[] spitData=dataLine.split(" ");
			cstmt.setInt(1,Integer.parseInt(spitData[0]));
			int len=spitData.length;
			for(int i=2;i<=len;i++){
			     cstmt.setString(i,spitData[i-1]);
			}
			count=count+cstmt.executeUpdate();
			dataLine=bf.readLine();
		}
		System.out.println("No of row Affected "+count);
		
		st=con.createStatement();
		rs=st.executeQuery("select a.regno,a.first_name,a.middle_name,a.last_name,b.gfirst_name,b.gmiddle_name,b.glast_name,c.isadmin, c.password from student_info a,g_info b, student_other_info c where c.regno=a.regno and a.regno=b.regno");
		ResultSetMetaData rsm=rs.getMetaData();
		for(int i=1;i<=rsm.getColumnCount();i++){
			bfw.write(rsm.getColumnName(i)+"\t");
		}
		bfw.newLine();bfw.write("===========================================================================================================================");
		while(rs.next()){
			bfw.newLine();
			bfw.write(rs.getString("regno")+"\t"+rs.getString("first_name")+"\t\t"+rs.getString("middle_name"));
			bfw.write(rs.getString("last_name")+"\t\t"+rs.getString("gfirst_name")+"\t\t"+rs.getString("gmiddle_name"));
			bfw.write(rs.getString("glast_name")+"\t\t"+rs.getString("isadmin")+"\t\t"+rs.getString("password"));
			
		}
		System.out.println("File Write Success !");
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	 finally{
			try {
				if(con!=null){
					con.close();
				}
				if(cstmt!=null){
					cstmt.close();
				}
				if(cstmt!=null){
					cstmt.close();
				}
				if(stream!=null){
					stream.close();
				}
				if(freader!=null){
					freader.close();
				}
				if(bf!=null){
					bf.close();
				}
				if(bfw!=null){
					bfw.close();
				}
				if(st!=null){
					st.close();
				}
				if(fo!=null){
					fo.close();
				}
				if(rs!=null){
					rs.close();
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
