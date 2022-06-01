<%@page import="com.jenish.shg.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>User List</title>

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

h1{
	diaplay: block;
	color: blue;
	text-align: center;
}

body{
	background-color: pink;
}
button{
	display: block;
	font-family: Times New Romans;
	font-size: 16px;
 	padding: 6px;
	background-color: green;
} -->
</style>

</head>

<body>
	<ul>
		<form method="post" action="log">
			<li><button name="button" value="Home">Home</button></li>
		</form>
		<form method="post" action="logout">
			<li><button name="button" value="logout">Logout</button></li>
		</form>
		
	</ul>
	<hr>
	<h1>Displaying User List</h1>

	<table align = "center">

		<tr>

			<th><b>SI.NO</b></th>

			<th><b>USER ID</b></th>

			<th><b>NAME</b></th>

			<th><b>GENDER</b></th>

			<th><b>ADDRESS</b></th>

			<th><b>EMAIL</b></th>

			<th><b>CONTACT NUMBER</b></th>

			<th><b>DATE OF BIRTH</b></th>

			<th><b>DATE OF JOINING</b></th>

			<th><b>TYPE</b></th>

		</tr>

		<%-- Fetching the attributes of the request object 

             which was previously set by the servlet  

              "StudentServlet.java" 

        --%>

		<%ArrayList<UserNames> un =  

            (ArrayList<UserNames>)request.getAttribute("proData"); 

		int siNo = 1;
		
        for(UserNames s: un){%>

		<%-- Arranging data in tabular form 

        --%>

		<form method="post" action="updateDelete">
			<tr>

				<td><%=siNo++%></td>

				<td><%=s.getpId()%></td>

				<td><%=s.getName()%></td>

				<td><%=s.getGender()%></td>

				<td><%=s.getAddress()%></td>

				<td><%=s.getEmail()%></td>

				<td><%=s.getPhNo()%></td>

				<td><%=s.getDob()%></td>

				<td><%=s.getJod()%></td>

				<td><%=s.getType()%></td>

				<input type="hidden" name="name" value=<%=s.getName() %>>

				<input type="hidden" name="id" value=<%=s.getpId() %>>

				<td><button name="button" value="delete">Delete</button></td>

			</tr>
		</form>

		<%}siNo = 1;%>

	</table>

	<br />

</body>

</html>
