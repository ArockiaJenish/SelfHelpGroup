package com.jenish.shg;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.util.*; 
import javax.naming.*; 
import javax.naming.directory.*;

public class RegisterServlet extends HttpServlet {
	
	private static final String INSERT_PRO = "INSERT INTO PROFILE VALUES(SEQ_PF.NEXTVAL,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),trunc(sysdate),'user')";
	private static final String SELECT_ID = "SELECT ID FROM PROFILE ORDER BY ID";
	private static final String INSERT_REC = "INSERT INTO RECORD VALUES(SEQ_REC.NEXTVAL,0,0,0,0,0,0,0,0,NULL,trunc(sysdate),?,0,0,NULL)";
	private static PreparedStatement ps = null;
	private static Connection con = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps1 = null;
	private static Statement st = null;
	private static int status = 0;
	private static RequestDispatcher rd = null;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String dob = req.getParameter("dob");
		String pass = req.getParameter("pass");
		String cpass = req.getParameter("cpass");
		
		EmailValidating ev = new EmailValidating();
		
		if (pass.equals(cpass)) {
			if(ev.isAddressValid(email) == true) 
				status = saveUser(name, gender, address, email, phone, dob, pass);
			else {
				pw.println("<h3><b>Please enter valied email address</b></h3>");
				rd = req.getRequestDispatcher("register.jsp");
				rd.include(req, res);
			}
				
			if (status > 0) {				
				pw.println("<h3><b>Registerd Successfuly...</b></h3>");
				rd = req.getRequestDispatcher("index.jsp");
				rd.include(req, res);
			} 
		}
		else {
			pw.println("<h3><b>Your password missmatching</b></h3>");
			rd = req.getRequestDispatcher("register.jsp");
			rd.include(req, res);
		}
			
		
		/*
		 * try {
		 * 
		 * Connection con = UserDAO.getCon();
		 * 
		 * ps = con.prepareStatement(SELECT); ps.setString(1, uName); rs =
		 * ps.executeQuery(); if(rs.next()) { pw.println("Login successfull!"); rd =
		 * req.getRequestDispatcher("dis"); rd.forward(req, res); } else {
		 * pw.println("Invalied User"); rd = req.getRequestDispatcher("logIn.html");
		 * rd.include(req, res); }
		 * 
		 * } catch(Exception e) { e.printStackTrace(); }
		 */
	}

	private int saveUser(String name, String gender, String address, String email, String phone, String dob,
			String pass) {
		// TODO Auto-generated method stub
		try {
			con = UserDAO.getCon();
			ps = con.prepareStatement(INSERT_PRO);
			ps.setString(1, name);
			ps.setString(2, gender);
			ps.setString(3, address);
			ps.setString(4, email);
			ps.setString(5, pass);
			ps.setString(6, phone);
			ps.setString(7, dob);
			status = ps.executeUpdate();
			
			st = con.createStatement();
			rs = st.executeQuery(SELECT_ID);
			int id = 0;
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
			ps1 = con.prepareStatement(INSERT_REC);
			ps1.setInt(1, id);
			status = ps1.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	
}
