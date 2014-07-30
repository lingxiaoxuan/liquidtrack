package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminServlet");
		
		HttpSession session = request.getSession();
		if ("1000".equals(session.getAttribute("RoleID"))){
			request.setAttribute("password", "");
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}
}
