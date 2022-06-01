package com.jenish.shg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpDelServlet extends HttpServlet {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static PreparedStatement ps2 = null;
	private static PreparedStatement ps3 = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	private static final String UPDATE_PROF = "UPDATE PROFILE SET NAME = ?, ADDRESS = ?, PH_NUM = ?, DOB = TO_DATE(?,'YYYY/MM/DD') WHERE ID = ?";
	private static final String UPDATE_REC = "UPDATE RECORD SET SAVING = ?, CHARGE = ?, TEMPAY_LOAN = ?, INTEREST = ?, UP_DATE = TRUNC(SYSDATE) WHERE REF_ID = ?";
	private static final String UPDATE_PAY_LOAN = "UPDATE RECORD SET PAY_LOAN = PAY_LOAN + TEMPAY_LOAN WHERE REF_ID = ?";
	private static final String UPDATE_BAL_LOAN = "UPDATE RECORD SET BAL_LOAN = LOAN - PAY_LOAN WHERE REF_ID = ?";
	private static final String UPDATE_LOAN = "UPDATE RECORD SET LOAN = ? , GM_DATE = TRUNC(SYSDATE) WHERE REF_ID = ?";
	private static final String UNPAID_INTEREST = "UPDATE RECORD SET LOAN = LOAN + INTEREST WHERE REF_ID = ?";
	private static final String UPDATE_SAVE_INTEREST = "UPDATE RECORD SET SAVINGS = SAVINGS+SAVING, INTERESTS = INTERESTS + INTEREST WHERE REF_ID = ?";
	private static final String DELETE_USER = "DELETE FROM PROFILE WHERE ID = ?";
	private static final String DELETE_RECORD = "DELETE FROM RECORD WHERE REF_ID = ?";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String btn = request.getParameter("button");

			if (btn.equals("savePro"))
				updatePro(request, response);
			else if (btn.equals("updateLoan")) {
				updateRecord(request, response);
			}
			else if (btn.equals("upMonthRec")) {
				updateMonthRec(request, response);
			}
			else if(btn.equals("delete"))
				delete(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		
		con = UserDAO.getCon();
		ps = con.prepareStatement(DELETE_USER);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		ps = con.prepareStatement(DELETE_RECORD);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		PrintWriter pw = response.getWriter();
		pw.println("<h4>Deleted "+name+"'s record...");
		
		DisplayServlet ds = new DisplayServlet();	
		List<UserNames> un = ds.getUserNames();
		request.setAttribute("proData", un);
		
		request.getRequestDispatcher("profileList.jsp").include(request, response);
	}

	/*
	 * private void updateGmDate(HttpServletRequest req, HttpServletResponse res)
	 * throws SQLException{ if(btn!=null) if(btn.equals("finished")) {
	 * 
	 * } }
	 */

	private void updateMonthRec(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

		String sSaving = request.getParameter("saving");
		double saving = Double.parseDouble(sSaving);
		String sCharge = request.getParameter("charge");
		double charge = Double.parseDouble(sCharge);
		String sPayLoan = request.getParameter("payLoan");
		double payLoan = Double.parseDouble(sPayLoan);
		String sInterest = request.getParameter("interest");
		double interest = Double.parseDouble(sInterest);
		int id = Integer.parseInt(request.getParameter("refId"));
		
		
		
		
		con = UserDAO.getCon();
		ps3 = con.prepareStatement(UPDATE_REC);
		ps3.setDouble(1, saving);
		ps3.setDouble(2, charge);
		ps3.setDouble(3, payLoan);
		ps3.setDouble(4, interest);
		ps3.setInt(5, id);
		ps3.executeUpdate();
		
		ps3 = con.prepareStatement(UPDATE_PAY_LOAN);
		ps3.setInt(1, id);
		ps3.executeUpdate();
		
		ps3 = con.prepareStatement(UPDATE_BAL_LOAN);
		ps3.setInt(1, id);
		ps3.executeUpdate();
		
		if(interest == 0) {
			ps3 = con.prepareStatement(UNPAID_INTEREST);
			ps3.setInt(1, id);
			ps3.executeUpdate();
		}
		
		/*
		 * ps3 = con.prepareStatement(UPDATE_GM_DATE); ps3.setInt(1, id);
		 * ps3.executeUpdate();
		 */
		
		ps3 = con.prepareStatement(UPDATE_SAVE_INTEREST);
		ps3.setInt(1, id);
		ps3.executeUpdate();
		
		
		
		PrintWriter pw = response.getWriter();
		pw.println("<h5>Record Updated Sucessfully...</h5>");
		
		DisplayServlet ds = new DisplayServlet();
		List<UserRecord> ur = ds.getUserRecord();
		request.setAttribute("userRec", ur);
		
		request.getRequestDispatcher("monthRec.jsp").include(request, response);
	}

	private void updateRecord(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

		/*
		 * String sSaving = request.getParameter("saving"); double saving =
		 * Double.parseDouble(sSaving); String sCharge = request.getParameter("charge");
		 * double charge = Double.parseDouble(sCharge); String sTempayLoan =
		 * request.getParameter("tempay_loan"); double payLoan =
		 * Double.parseDouble(sTempayLoan);
		 */
		String sLoan = request.getParameter("loan");
		double loan = Double.parseDouble(sLoan);
		String sId = request.getParameter("refId");
		int id = Integer.parseInt(sId);

		con = UserDAO.getCon();
		ps2 = con.prepareStatement(UPDATE_LOAN);
		ps2.setDouble(1, loan);
		ps2.setInt(2, id);
		ps2.executeUpdate();
		
		PrintWriter pw = response.getWriter();
		pw.println("<h4>Loan Updated Sucessfully...</h4>");
		request.getRequestDispatcher("loan.jsp").include(request, response);
	}

	private void updatePro(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PrintWriter pw = response.getWriter();

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phNo = request.getParameter("phNo");
		String dob = request.getParameter("dob");

		Cookie ck[] = request.getCookies();
		if (ck != null) {
			String sid = ck[0].getValue();
			int id = Integer.parseInt(sid);

			con = UserDAO.getCon();
			ps = con.prepareStatement(UPDATE_PROF);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, phNo);
			ps.setString(4, dob);
			ps.setInt(5, id);

			int status = ps.executeUpdate();
			if (status > 0)
				pw.println("<h4>Saved Successfuly...</h4>");
			else
				pw.println("<h4>Sorry unable to save</h4>");
			request.getRequestDispatcher("log").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
