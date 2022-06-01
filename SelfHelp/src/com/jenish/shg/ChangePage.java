package com.jenish.shg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePage
 */
public class ChangePage extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		
		String getBtn = request.getParameter("button");
		
		if(getBtn.equals("register")) {
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
		}else if(getBtn.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}else if(getBtn.equals("editPro")) {
			LoginServlet ls = new LoginServlet();
			List<UserNames> un = ls.getUser();
			
			request.setAttribute("user", un);
			RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
			rd.include(request, response);
		}else if(getBtn.equals("giveLoan")) {
			//LoginServlet ls = new LoginServlet();
			DisplayServlet ds = new DisplayServlet();
			List<UserRecord> ur = ds.getUserRecord();
			double zero = 0.0;
			request.setAttribute("monthLoan", zero);
			request.setAttribute("userRecord", ur);
			
			RequestDispatcher rd = request.getRequestDispatcher("loan.jsp");
			rd.include(request, response);
		}else if(getBtn.equals("monthRecord")) {
			
			DisplayServlet ds = new DisplayServlet();
			List<UserRecord> ur = ds.getUserRecord();
			request.setAttribute("userRec", ur);
			
			request.getRequestDispatcher("monthRec.jsp").include(request, response);
		}else if(getBtn.equals("home")){
			response.sendRedirect("log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
