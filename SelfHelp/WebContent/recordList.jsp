<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.jenish.shg.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Record</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
</head>
<body>
	<ul>
		<form method="post" action="log">
			<li><button name="button" value="Home">Home</button></li>
		</form>
		<form method="post" action="change">
			<li><button name="button" value="giveLoan">Give Loan</button></li>
			<li><button name="button" value="monthRecord">Enter Monthly Record</button></li>
		</form>
		<form method="post" action="logout">
			<li><button name="button" value="logout">Logout</button></li>
		</form>
	</ul>
	<hr>
	<h1 align="center">Displaying User Record</h1>
	
	<table border="1" width="90%" align="center">

		<tr bgcolor="silver">
			
			<th><b>SI.NO</b></th>
			
			<th><b>NAME</b></th>

			<th><b>SAVING</b></th>

			<th><b>CHARGE</b></th>

			<th><b>LOAN</b></th>
			
			<th><b>BALANCE LOAN</b></th>

			<th><b>PAID LOAN</b></th>

			<th><b>INTEREST</b></th>
			
			<th><b>FINAL AMOUNT</b></th>
			
			<th><b>LOAN ISSUED DATE</b></th>

		</tr>

		

		<%ArrayList<UserRecord> ur =  

            (ArrayList<UserRecord>)request.getAttribute("recordData"); 
		
		int siNo = 1;

        for(UserRecord r: ur){%>

		<%-- Arranging data in tabular form 

        --%>

		<tr>

			<td><%=siNo++%></td>
			
			<td><%=r.getName()%></td>

			<td><%=r.getSaving()%></td>

			<td><%=r.getCharge()%></td>

			<td><%=r.getLoan()%></td>
			
			<td><%=r.getBalLoan()%></td>

			<td><%=r.getPayLoan()%></td>

			<td><%=r.getInterest()%></td>
			
			<td><%=r.getFinalAmount()%></td>
			
			<td><%=r.getGmDate()%></td>

		</tr>

		<%}siNo = 1;%>

	</table>
</body>
</html>