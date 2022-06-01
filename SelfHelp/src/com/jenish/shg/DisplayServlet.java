package com.jenish.shg;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayServlet
 */

public class DisplayServlet extends HttpServlet {

	private static final String SELECT_PROFILE = "SELECT * FROM PROFILE";
	private static final String UPDATE_DATE = "UPDATE RECORD SET UP_DATE = TRUNC(SYSDATE)";
	private static final String UPDATE_INTEREST = "UPDATE RECORD SET INTEREST = (BAL_LOAN * 0.02 * ((UP_DATE-CAL_DATE)/30))";
	private static final String SELECT_RECORD = "SELECT REF_ID, NAME, SAVING, CHARGE, LOAN, PAY_LOAN, TEMPAY_LOAN, BAL_LOAN, INTEREST, GET_AMOUNT, GM_DATE FROM PROFILE JOIN RECORD ON PROFILE.ID = RECORD.REF_ID";
	private static Connection con = null;
	private static Statement pSt = null;
	private static ResultSet pRs = null;
	private static Statement rSt = null;
	private static ResultSet rRs = null;
	private static PreparedStatement ps = null;
	private static List<UserRecord> ur = null;
	private static List<UserNames> un = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		String val = request.getParameter("button");
		if (val.equals("profile")) {
			try {
				getUserProfile(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (val.equals("record")) {
			try {
				getUserRecord(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void getUserRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		con = UserDAO.getCon();

		ps = con.prepareStatement(UPDATE_DATE);
		ps.executeUpdate();

		ps = con.prepareStatement(UPDATE_INTEREST);
		ps.executeUpdate();

		ur = new ArrayList<UserRecord>();
		// int count = 0;
		rSt = con.createStatement();
		rRs = rSt.executeQuery(SELECT_RECORD);
		if (rRs != null)
			while (rRs.next()) {
				ur.add(new UserRecord(rRs.getInt("ref_id"), rRs.getString("name"), rRs.getDouble("saving"),
						rRs.getDouble("charge"), rRs.getDouble("loan"), rRs.getDouble("pay_loan"),
						rRs.getDouble("tempay_loan"), rRs.getDouble("bal_loan"), rRs.getDouble("interest"),
						rRs.getDouble("get_amount"), rRs.getDate("gm_date")));
				// count++;
			}

		/*
		 * PrintWriter pw = response.getWriter();
		 * pw.println("<h2>the count value is "+count+"</h2>");
		 */
		request.setAttribute("recordData", ur);
		RequestDispatcher rd = request.getRequestDispatcher("recordList.jsp");
		rd.include(request, response);

	}

	private void getUserProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ParseException, ServletException, IOException {

		con = UserDAO.getCon();

		pSt = con.createStatement();
		pRs = pSt.executeQuery(SELECT_PROFILE);

		un = new ArrayList<UserNames>();

		if (pRs != null)
			while (pRs.next()) {
				un.add(new UserNames(pRs.getString(1), pRs.getString(2), pRs.getString(3), pRs.getString(4),
						pRs.getString(5), pRs.getString(6), pRs.getString(7), pRs.getDate(8), pRs.getDate(9),
						pRs.getString(10)));
			}

		request.setAttribute("proData", un);

		RequestDispatcher rd = request.getRequestDispatcher("profileList.jsp");
		rd.include(request, response);
	}

	public List<UserRecord> getUserRecord() {
		return ur;
	}
	
	public List<UserNames> getUserNames(){
		return un;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		pw.println("Enterd into Display Servlet<br>");
		doGet(request, response);
	}

}
