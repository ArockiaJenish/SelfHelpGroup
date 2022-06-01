<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jenish.shg.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Monthly Record</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
</head>
<body>
	<ul>
		<form method = "post" action = "log">
			<li><button name = "button" value = "Home">Home</button></li>
		</form>
		<form method="post" action="logout">
			<li><button name="button" value="logout">Logout</button></li>
		</form>
	</ul>
	<h2 align="center">Enter Monthly Record here...</h2>
	<table align="center" width="90%" border = "1px">
		<tr>
			<th><b>NAME</b></th>
			<th><b>SAVING</b></th>
			<th><b>CHARGE</b></th>
			<th><b>BALANCE LOAN</b></th>
			<th><b>PAID LOAN</b></th>
			<th><b>PAID LOAN (this month)</b></th>
			<th><b>INTEREST</b></th>

		</tr>
		<%
		List<UserRecord> ur = (ArrayList<UserRecord>) request.getAttribute("userRec");
		for (UserRecord r : ur) {
		%>

		<form method="post" action="updateDelete">
			<tr>
				<td><%=r.getName()%></td>
				<td><input type="text" name="saving" value="<%=r.getSaving()%>"></td>
				<td><input type="text" name="charge" value="<%=r.getCharge()%>"></td>
				<td><%=r.getBalLoan()%></td>
				<td><%=r.getPayLoan()%></td>
				<td><input type="text" name="payLoan"
					value="0.0"></td>
				<td><input type="text" name="interest"
					value="<%=r.getInterest()%>"></td>
				<input type="hidden" name="refId" value="<%=r.getRefId()%>">
				<td><button name="button" value="upMonthRec">Update</button></td>
			</tr>
		</form>
		<%
		}
		%>
	</table>
	<form method = "post" action = "finish">
		<a>Once you finish the current month record kindly </a><button name = "button" value = "finished">click here</button></a>
	</form>
</body>
</html>