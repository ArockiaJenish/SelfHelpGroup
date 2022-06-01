<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jenish.shg.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
</head>
<body>
	<ul>
		<form method="post" action="log">
			<li><button name="button" value="Home">Home</button></li>
		</form>
		<form method="post" action="logout">
			<button name="button" value="logout">Logout</button>
		</form>
	</ul>
	<hr>
	<%double monthLoan = (double)request.getAttribute("monthLoan");
	if(monthLoan!=0.0){%>
	<h2 align="center">The loan amount is <%=monthLoan%></h2>
	<%}%>
	<table align="center">
		<tr>
			<th><b>NAME</b></th>
			<th><b>LOAN</b></th>
		</tr>
		<%List<UserRecord> ur = (ArrayList<UserRecord>)request.getAttribute("userRecord");
		for(UserRecord r: ur){%>
		<form method="post" action="updateDelete">
			<tr>
				<td><%=r.getName()%></td>
				<td><input type="text" name="loan" value="<%=r.getLoan()%>"></td>
				<td><button name="button" value="updateLoan">Update</button></td>
			</tr>
			<input type="hidden" name="refId" value="<%=r.getRefId()%>">
		</form>
		<%}%>
	</table>

</body>
</html>