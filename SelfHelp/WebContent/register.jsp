<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User registration</title>
<link rel = "stylesheet" type = "text/css" href = "css/align.css">
</head>
<body>
	<!-- <form action = "disp" method = "get">
		<button value = "profile" name = "button">View User Names</button>
		<button value = "record" name = "button">View User Records</button>
		 border="2" bgcolor="gold" style="color:black; text-align:center; width:60%;"
	</form>  -->

        <form action="reg" method = "post">

            <center>

                <br><br><h1>User Registration</h1><br><br><br><br>

                <table>

                    <tr>

                        <td>

                            <b>Name:</b>

                        </td>

                        <td>

                            <input type="text" placeholder = "Name" name="name" style="padding:2px 50px;">

                        </td>

                    </tr>

					<tr>

                        <td>

                            <b>Gender:</b>                       

                        </td>

                        <td>

							<input type="radio" name="gender" value="Male">Male
							<input type="radio" name="gender" value="Female">Female

                        </td></tr>

					
					<tr>

                        <td>

                            <b>Address</b>

                        </td>

                        <td>
                        	<textarea rows="4" cols="36" name="address" placeholder = "Enter your Address Here..."></textarea>
                        </td>

                    </tr>

                    <tr>

                        <td>

                            <b>Email-ID:</b>

                        </td>

                        <td>

                            <input type="email" placeholder = "Email-ID" name="email" style="padding:2px 50px;">

                        </td>

                    </tr>
					
					 <tr>

                        <td>

                            <b>Phone No:</b>

                        </td>

                        <td>

                            <input type="text" placeholder = "Phone number" name="phone" style="padding:2px 50px;">

                        </td>

                    </tr>
					
					<tr>

                        <td>

                            <b>Date of Birth</b>

                        </td>

                        <td>

                            <input type="date" placeholder = "DD/MM/YYYY" name="dob" style="padding:2px 50px;">

                        </td>

                    </tr>
					
                    <tr>

                        <td>

                            <b>Password:</b>

                        </td>

                        <td>

                            <input type="password" placeholder = "Enter password" name="pass" style="padding:2px 50px;">

                        </td>

                    </tr>

                    <tr>

                        <td>

                            <b>Confirm Password:</b>

                        </td>

                        <td>

                            <input type="password" placeholder = "Confirm password" name="cpass" style="padding:2px 50px;">

                        </td>

                    </tr>  

                    <tr>

                        <td>

                            

                        </td>

                        <td>
							
							<input type="reset" value="Reset">
                            <input type="submit" value="SignUp">

                        </td>

                    </tr>

                </table>

        </form>

    </body>
</html>