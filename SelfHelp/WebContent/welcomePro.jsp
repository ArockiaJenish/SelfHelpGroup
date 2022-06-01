<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.jenish.shg.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
<!-- <style type="text/css">
ul {
	list-style-type: none;
	margin: 0;
	padding: 10px 0 0 100px;
	overflow: hidden;
}

li {
	float: left;
}

li a {
	display: block;
	padding: 8px;
	background-color: #dddddd;
}

h1 {
	diaplay: block;
	color: blue;
	text-align: center;
}

body {
	background-color: pink;
}

button {
	display: block;
	font-family: Times New Romans;
	font-size: 16px;
	padding: 6px;
	background-color: green;
}
</style> -->
</head>
<body >
	<% List<String> details = (ArrayList<String>)request.getAttribute("name");
	String name = (String)details.get(0);
	String type = (String)details.get(1);
	String adNum = (String)details.get(2);
	String adEmail = (String)details.get(3);%>
	<ul>
		<form method="get" action="log">
			<li><button name="button" value="myPro">My Details</button></li>
			<li><button name="button" value="myRec">Amount Details</button></li>
		</form>
		
		<%
		if (type.equals("admin")) {
		%>
		<form action="disp" method="get">
			<li><button value="profile" name="button">View User Names</button></li>
			<li><button value="record" name="button">View User Records</button></li>
		</form>
		<%
		}
		%>
		<form method="post" action="logout">
			<li><button name="button" value="logout">Logout</button></li>
		</form>
	</ul>
	<hr>
	<h1>Welcome <%=name%></h1>
	<p>
	Hi <%=name%>, welcome to Self Help Group (SHG) portal. This portal helps you to know your
	details. And also you can contact your leader through this mobile number: <strong><%=adNum%></strong> and
	email: <strong><%=adEmail %></strong>.
	</p>
</body>
</html>