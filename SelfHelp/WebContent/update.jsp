<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.jenish.shg.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
</head>
<body>
	<ul>
		<form method="post" action="log">
			<li><button name="button" value="Home">Home</button></li>
		</form>
		<form method="post" action="logout">
			<li><button align="right" value="logout">Logout</button></li>
		</form>
	</ul>
	<%List<UserNames> un = (ArrayList<UserNames>)request.getAttribute("user");
	
	Cookie ck[] = request.getCookies();
	String sid;
	int id = 0;
	int verifyId = 0;
	int count = 0;
	if(ck != null) {
		sid = ck[0].getValue();
		id = Integer.parseInt(sid);
	}
	for(UserNames u: un){
		
		if(u.getpId()==id && count==0){	
			count++;
	//UserNames u = (UserNames)un.get(0);
	%>
	<h4>You can edit your details here...</h4>
	<form method="post" action="updateDelete">
		<table>
			<tr>
				<td></td>
				<td><input type="hidden" name="id" value="<%=u.getpId()%>"></td>
			</tr>
			<tr>
				<td>NAME:</td>
				<td><input type="text" name="name" value="<%=u.getName()%>"></td>
			</tr>
			<tr>
				<td>ADDRESS:</td>
				<td><textarea rows="4" cols="16" name="address"><%=u.getAddress()%></textarea></td>
			</tr>
			<tr>
				<td>CONTACT NUMBER:</td>
				<td><input type="text" name="phNo" value="<%=u.getPhNo()%>"></td>
			</tr>
			<tr>
				<td>DATE OF BIRTH:</td>
				<td><input type="date" name="dob" value="<%=u.getDob()%>"></td>
			</tr>
			<tr>
				<td></td>
				<td><button name="button" value="savePro">Edit & Save</button></td>
			</tr>
		</table>
	</form>
	<%}}un.clear();%>
</body>
</html>