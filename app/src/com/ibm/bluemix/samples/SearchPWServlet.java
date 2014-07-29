package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SearchPWServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateProfileServlet get");
		
		String notesID = request.getParameter("NotesID");
		try {
			EntityProfile profile = db.getProfile(notesID);
			if (profile != null) {
				request.setAttribute("password", profile.getPassword());
			}
			else{
				request.setAttribute("password", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}
}
