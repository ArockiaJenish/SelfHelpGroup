<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.jenish.shg.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.io.*"%>

<!DOCTYPE html>
<html>
<head>
<meta name = "viewport" content = "width=device-width, initial-scale=1.0">
<title>User Profile</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
</head>
<body>
	<div class = "container">
		<div class = "navigation">
			<ul>
				<li>
					<form method="post" action="log">
						<button name="button" value="Home">Home</button>
					</form>
				</li>
				<li>
					<form method="post" action="change">
						<button name="button" value="editPro">Edit</button>
					</form>
				</li>
				<li>
					<form method="post" action="logout">
						<button name="button" value="logout">Logout</button>
					</form>
				</li>
			</ul>
		</div>
	</div>
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

	<hr>
	
	<%List<UserNames> un = (ArrayList<UserNames>)request.getAttribute("singUser");
	
	PrintWriter pw = response.getWriter();
	
	Cookie ck[] = request.getCookies();
	String sid;
	int id = 0;
	//int verifyId = 0;
	boolean printed = false;
	if(ck != null) {
		sid = ck[0].getValue();
		id = Integer.parseInt(sid);
	}
	//pw.println("<h3>Cookie id = "+id+"</h3>");
	for(UserNames u: un){
		
		//pw.println("<h3>u.getpId() = "+u.getpId()+"</h3>");
		if(u.getpId()==id && printed==false){	
			printed = true;%>

		<%-- <h1>Welcome <%=u.getName() %></h1> --%>
		<h4>Here is your details</h4>
		<table border = "1px" align = "center">
		<tr>
			<td>GENDER: </td>
			<td><%=u.getGender()%></td>
		</tr>
		<tr>
			<td>ADDRESS: </td>
			<td><%=u.getAddress()%></td>
		</tr>
		<tr>
			<td>EMAIL: </td>
			<td><%=u.getEmail()%></td>
		</tr>
		<tr>
			<td>CONTACT NUMBER: </td>
			<td><%=u.getPhNo()%></td>
		</tr>
		<tr>
			<td>DATE OF BIRTH: </td>
			<td><%=u.getDob()%></td>
		</tr>
		<tr>
			<td>DATE OF JOINING: </td>
			<td><%=u.getJod()%></td>
		</tr>
	</table>
	<%}
	} printed = false;%>
</body>
</html>