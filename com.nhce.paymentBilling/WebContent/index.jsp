<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<title>
			Welcome Page
		</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>
	<div id="outer">
	<div id="header">
			
				<div id="logo">
					<h1>
						Payment Billing System
					</h1>
				</div>	
		</div>
			<div id="banner">
				<div class="captions">
				
				</div>
				<img src="images/banner.jpg" alt="" />
			
			</div>
			
			<div id="nav" align="center" height=15%>
					<ul>
						<li class="first">
							<a href="home.jsp">Home</a>
						</li>
						<li>
							<a href="generalinfo.jsp">New Registration</a>
						</li>
						<li>
							<a href="modify.jsp">Modify Detail</a>
						</li>
						<li>
							<a href="ahome.jsp">Administrator</a>
						</li>
						
						<li>
							<a href="contactus.jsp">Contact us</a>
						</li>
					</ul><br class="clear" />
				</div>
				
				<% 
					if(request.getAttribute("notlogin_msg")!=null){
					out.print("<font size='2' color='red' m>");
					out.print(request.getAttribute("notlogin_msg"));
					out.print("</font>");
					}
					%>
				
				
				
				<% 
					if(request.getAttribute("Error")!=null){
					out.print("<font size='2' color='red' m>");
					out.print(request.getAttribute("Error"));
					out.print("</font>");
					}
					%>
			<div id="main">
			<table align="right">
			
			<tr align="center"><td style="color: navy;"><B>Notice</B></td></tr>
			<tr><td><div><marquee direction="up" width="300px" truespeed="truespeed" onmouseover="stop()" onmouseout="start()" style="color: red;">Last Date of fee Submission is Nov 12, 2016
															     After that You'll have to pay 500 INR 
															     per day as a fine and your tutuion fee
															     exclusively </marquee></div></td></tr></table>
		<center><a href="admin.jsp"><font style="color: navy; ">Login As Administrator</font></a></center>
	<div id="sidebar">
					<div class="box">
					
					<form method="post" action="loginprocess.jsp" >
					<table>
					<tr><td style="color:#456789;"><B> Login Form</B></td></tr>
					<tr><td><br></td></tr>
					<tr><td>Branch:</td><td><select name="branch">
					<option>Select a Branch</option>
					<option>Ahemedabad</option>
					<option>Bengaluru</option>
					<option>Chenai</option>
					<option>Delhi</option>
					<option>Jodhpur</option>
					</select></td></tr>
					<tr><td><br></td></tr>
					<tr><td>User Name:</td><td><input type="text" name="username"/></td></tr>
								<tr><td><br></td></tr>
					<tr><td>Password:</td><td><input type="password" name="userpass"/></td></tr>	
							<tr><td><br></td></tr>
					<tr><td>      </td><td><input type="submit" value="Sign in"></td></tr>
					</table>
					</form>		 
						</div>
				</div><br class="clear" />
			</div>		



	
			<div id="header">
					<h4>
						<center><font color=#234567>This is a <b>NHCE</b> Bengaluru, College Mini Project</font></center></font></a>
					</h4>
			</div>

</div>
	</body>
</html>