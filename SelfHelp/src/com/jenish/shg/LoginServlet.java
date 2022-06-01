package com.jenish.shg;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps2 = null;
	private static ResultSet rs2 = null;
	private static PreparedStatement ps3 = null;
	private static ResultSet rs3 = null;
	private static PreparedStatement ps4 = null;
	private static ResultSet rs4 = null;
	private static PreparedStatement ps5 = null;
	private static ResultSet rs5 = null;
	private static final String CHECK = "SELECT ID, NAME, TYPE FROM PROFILE WHERE EMAIL = ? AND PASSWORD = ?";
	private static final String NAME_TYPE = "SELECT NAME, TYPE FROM PROFILE WHERE ID = ?";
	private static final String AD_NUMBER = "SELECT PH_NUM, EMAIL FROM PROFILE WHERE TYPE = ?";
	private static final String SELECT_PROF_ID = "SELECT * FROM PROFILE WHERE ID = ?";
	private static final String SELECT_REC_ID = "SELECT NAME, SAVING, CHARGE, LOAN,PAY_LOAN, TEMPAY_LOAN, BAL_LOAN, INTEREST, GET_AMOUNT, GM_DATE, UP_DATE FROM PROFILE JOIN RECORD ON PROFILE.ID = RECORD.REF_ID WHERE REF_ID = ?";
	private static List<UserNames> un = new ArrayList<UserNames>();
	private static List<UserRecord> ur = new ArrayList<UserRecord>();
	private static int id;
	private static String name;
	private static String gender;
	private static String address;
	private static String email;
	private static String phNo;
	private static Date dob;
	private static Date jod;
	private static String type; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.setContentType("html/text");

		con = UserDAO.getCon();

		try {

			login(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PrintWriter pw = response.getWriter();

		
		
		String btn = request.getParameter("button");
		
		if(btn == null) {
			String vemail = request.getParameter("email");
			String vpass = request.getParameter("password");
			int Id = 0;

			ps = con.prepareStatement(CHECK);
			ps.setString(1, vemail);
			ps.setString(2, vpass);
			rs = ps.executeQuery();
			if (rs.next()) {
				Id = rs.getInt("id");
				Cookie ck = new Cookie("id", Integer.toString(Id));
				response.addCookie(ck);
				welcome(rs, request, response);
			} else {
				pw.println("<h2>Invalied User<h2>");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}
		}
		//pw.println("<h3>Control is now on LoginServlet doGet()</h3>");
		if(btn != null) {
			if(btn.equals("myPro")) {
				userDetails(request, response);
			}
			if(btn.equals("myRec")) {
				userRecord(request, response);
			}
		}
			
				
	}

	
	private void userRecord(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Cookie ck[] = request.getCookies();
		String sid;
		int id = 0;
		if(ck != null) {
			sid = ck[0].getValue();
			//pw.println("<h3>Cookie value is = "+sid+"</h3>");
			id = Integer.parseInt(sid);
		}
			
		con = UserDAO.getCon();
		ps4 = con.prepareStatement(SELECT_REC_ID);
		ps4.setInt(1, id);
		rs4 = ps4.executeQuery();
		
		if(rs4.next()) {
			String name = rs4.getString("name");
			double saving = rs4.getInt("saving");
			double charge = rs4.getDouble("charge");
			double loan = rs4.getDouble("loan");
			double payLoan = rs4.getDouble("pay_loan");
			double tempayLoan = rs4.getDouble("tempay_loan");
			double balLoan = rs4.getDouble("bal_loan");
			double interest = rs4.getDouble("interest");
			double getAmount = rs4.getDouble("get_amount");
			Date getMoneyDate = rs4.getDate("gm_date");
			
			UserRecord r = new UserRecord(id, name, saving, charge, loan, payLoan, tempayLoan, balLoan, interest, getAmount, getMoneyDate);
			ur.add(r);
		}	
		request.setAttribute("singRecord", ur);
		RequestDispatcher rd = request.getRequestDispatcher("rec_view.jsp");
		rd.include(request, response);
	}

	private void welcome(ResultSet rs4, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String user = rs4.getString("type");
		String name = rs4.getString("name");
		String phNum = "";
		String adEmail = "";
		ps5 = con.prepareStatement(AD_NUMBER);
		ps5.setString(1, "admin");
		rs5 = ps5.executeQuery();
		if(rs5!=null)
			while(rs5.next()) {
				phNum = rs5.getString("ph_num");
				adEmail = rs5.getString("email");
			}
		List<String> details = new ArrayList<String>();
		details.add(name);
		details.add(user);
		details.add(phNum);
		details.add(adEmail);
		/*
		 * PrintWriter pw = response.getWriter(); pw.println("Admin phNumber = "+phNum);
		 * pw.println("Admin Email = "+adEmail);
		 */
		request.setAttribute("name", details);
		request.getRequestDispatcher("welcomePro.jsp").include(request, response);
	}

	private void userDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		Cookie ck[] = request.getCookies();
		if(ck!=null) {
			String sid = ck[0].getValue();
			int detailId = Integer.parseInt(sid);
			
			ps2 = con.prepareStatement(SELECT_PROF_ID);
			ps2.setInt(1, detailId);
			rs2 = ps2.executeQuery();
			
			if(rs2.next()) {
				id = rs2.getInt(1);
				name = rs2.getString("name");
				gender = rs2.getString("gender");
				address = rs2.getString("address");
				email = rs2.getString("email");
				phNo = rs2.getString("ph_num");
				dob = rs2.getDate("dob");
				jod = rs2.getDate("jo_date");
				type = rs2.getString("type");
				UserNames u = new UserNames(id, name, gender, address, email, phNo, dob, jod, type);
				un.add(u);
			}

			request.setAttribute("singUser", un);
			RequestDispatcher rd = request.getRequestDispatcher("pro_view.jsp");
			rd.include(request, response);
		}
		
	}

	public List<UserNames> getUser() {
		return un;
	}
	
	
	public List<UserRecord> getUserRecord() {
		return ur;
	}
	 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		//pw.println("<h3>Updated Successfully</h3>");
		Cookie ck[] = request.getCookies();
		if(ck!=null) {
			String sid = ck[0].getValue();
			int nid = Integer.parseInt(sid);
			
			try {
				con = UserDAO.getCon();
				ps3 = con.prepareStatement(NAME_TYPE);
				ps3.setInt(1, nid);
				rs3 = ps3.executeQuery();
				
				if(rs3.next()) {
					welcome(rs3, request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}