package com.jenish.shg;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		Cookie ck = new Cookie("name","");
		ck.setMaxAge(0);
		response.addCookie(ck);
		LoginServlet ls = new LoginServlet();
		DisplayServlet ds = new DisplayServlet();
		List<UserNames> un = ls.getUser();
		List<UserRecord> ur = ds.getUserRecord();
		List<UserRecord> url = ls.getUserRecord();
		if (un != null)
			un.clear();
		if(ur != null)
			ur.clear();
		if(url != null)
			url.clear();
		 
			
		pw.println("<h3>logged out successfuly...</h3>");
		request.getRequestDispatcher("index.jsp").include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}