package com.lalit.actions;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.lalit.beans.Education;
import com.lalit.dao.PersonDao;
import com.lalit.pool.ConnectionPool;
@WebServlet("AddPersonFormServlet")
public class AddPersonFormServlet extends HttpServlet{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	 Connection con = new ConnectionPool().checkOut();
    	 List<Education> eduList= PersonDao.getAllEducations(con);
    	 PrintWriter out=resp.getWriter();
    	 out.println("<form action='addPerson.do' method='POST' name=form1>");
    	 out.println("ID :<input type='text' name='id'/><br>");
    	 out.println("Name :<input type='text' name='name'/><br>");
    	 out.println("Age :<input type='text' name='age'/><br>");
    	 out.println("Educations:  <select name='education'>");
    	 Education edu=null;
    	 for(int i=0;i<eduList.size();i++){
    		 edu=eduList.get(i);
    		 out.println("<option value='"+edu.getId()+"'"+edu.getName()+"</option>");
    	 }
    	 out.println("</select><br><input type='submit' value='Submit'/></form>");
    	 
    }
}
