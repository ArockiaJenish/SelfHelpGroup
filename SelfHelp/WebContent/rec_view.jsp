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
<title>RecordView</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
</head>
<body>
	<ul>
		<li><form method="post" action="log">
			<button name="button" value="Home">Home</button>
		</form></li>
		<li><form method="post" action="logout">
			<button name="button" value="logout">Logout</button>
		</form></li>
	</ul>
	<hr>
	<%List<UserRecord> ur = (ArrayList<UserRecord>)request.getAttribute("singRecord");
	PrintWriter pw = response.getWriter();
	
	Cookie ck[] = request.getCookies();
	String sId;
	int id=0;
	boolean printed = false;
	
	if(ck!=null){
		sId = ck[0].getValue();
		id = Integer.parseInt(sId);
	}
	
	for(UserRecord r: ur){
		if(r.getRefId()==id && printed == false){%>
	<h4>Here is your amount Details</h4>
	<table align = "center">
		<tr><td>Saving</td><td><%=r.getSaving()%></td></tr>
		<tr><td>Charge</td><td><%=r.getCharge()%></td></tr>
		<tr><td>Total Loan</td><td><%=r.getLoan()%></td></tr>
		<tr><td>Paid Loan</td><td><%=r.getPayLoan()%></td></tr>
		<tr><td>Paid Loan(this month)</td><td><%=r.getTempayLoan()%></td></tr>
		<tr><td>Balance Loan</td><td><%=r.getBalLoan()%></td></tr>
		<tr><td>Interest</td><td><%=r.getInterest()%></td></tr>
		<tr><td>Final Amount</td><td><%=r.getFinalAmount()%></td></tr>
		<tr><td>Date of get loan</td><td><%=r.getGmDate()%></td></tr>
	</table><%printed = true;
		}
	}printed = false; %>
</body>
</html>