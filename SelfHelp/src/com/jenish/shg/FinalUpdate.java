package com.jenish.shg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FinalUpdate
 */
//@WebServlet("/FinalUpdate")
public class FinalUpdate extends HttpServlet {
	
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static Statement st2 = null;
	private static ResultSet rs2 = null;
	private static PreparedStatement ps2 = null;
	
	private static final String UPDATE_CAL_DATE = "UPDATE RECORD SET CAL_DATE = TRUNC(SYSDATE)";
	private static final String INSERT_ALL = "INSERT INTO OVERALL VALUES(?, ?, ?, TRUNC(SYSDATE))";
	//To do
	private static final String SELECT_FOR_HIS = "SELECT REF_ID, BAL_LOAN, PAY_LOAN, CHARGE, INTEREST, SAVING FROM RECORD";
	private static final String INSERT_HIS = "INSERT INTO HISRECORD VALUES(SEQ_HIS, ?, ?, ?, ?, ?, TRUNC(SYSDATE), ?)";
	private static final String UPDATE_F_AMOUNT = "UPDATE RECORD SET GET_AMOUNT = ?";
	private static final String CALCULATE_RECORD = "SELECT SAVING, CHARGE, TEMPAY_LOAN, INTEREST FROM RECORD";
	private static final String CALCULATE_GIVE_LOAN = "SELECT SAVINGS, INTERESTS FROM RECORD";
	
	private static final long serialVersionUID = 1L;
       
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String btn = request.getParameter("button");
		
		try {
			if(btn.equals("finished")) {
				updateFinals(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void updateFinals(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		double totTempayLoan = 0.0;
		double totCharge = 0.0;
		double totSaving = 0.0;
		double totInterest = 0.0;
		double totSavings = 0.0;
		double totInterests = 0.0;
		double allOverAmount = 0.0;
		double colLoan = 0.0;
		double eachPersonAmount = 0.0;
		int count = 0;
		
		con = UserDAO.getCon();
		st = con.createStatement();
		rs = st.executeQuery(CALCULATE_RECORD);
		while(rs.next()) {
			totTempayLoan += rs.getDouble("tempay_loan");
			totCharge += rs.getDouble("charge");
			totSaving += rs.getDouble("saving");
			totInterest += rs.getDouble("interest");
		}
		colLoan = totSaving + totInterest + totTempayLoan; //Monthly collection
		
		st = con.createStatement();
		rs = st.executeQuery(CALCULATE_GIVE_LOAN);
		while(rs.next()) {
			totSavings += rs.getDouble("savings");
			totInterests += rs.getDouble("interests");
			count++;
		}
		
		allOverAmount = totSavings + totInterests;
		eachPersonAmount = allOverAmount / count;
		
		ps = con.prepareStatement(UPDATE_F_AMOUNT);
		ps.setDouble(1, eachPersonAmount);
		ps.executeUpdate();
		
		ps = con.prepareStatement(INSERT_ALL);
		ps.setDouble(1, totSaving);
		ps.setDouble(2, totInterest);
		ps.setDouble(3, totCharge);
		ps.executeUpdate();
		
		ps = con.prepareStatement(UPDATE_CAL_DATE);
		ps.executeUpdate();
		
		/*
		 * st2 = con.createStatement(); rs2 = st2.executeQuery(SELECT_FOR_HIS);
		 * if(rs2!=null) while(rs2.next()) {
		 * 
		 * ps2 = con.prepareStatement(INSERT_HIS); ps2.setDouble(1,
		 * rs2.getDouble("bal_loan")); ps2.setDouble(2, rs2.getDouble("pay_loan"));
		 * ps2.setDouble(3, rs2.getDouble("charge")); ps2.setDouble(4,
		 * rs2.getDouble("pay_loan")); ps2.setDouble(5, rs2.getDouble("saving"));
		 * ps2.setDouble(6, rs2.getDouble("ref_id")); ps2.executeUpdate();
		 * 
		 * }
		 */
		 
		DisplayServlet ds = new DisplayServlet();
		List<UserRecord> ur = ds.getUserRecord(); 
		
		request.setAttribute("userRecord", ur);
		request.setAttribute("monthLoan", colLoan);
		request.getRequestDispatcher("loan.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
