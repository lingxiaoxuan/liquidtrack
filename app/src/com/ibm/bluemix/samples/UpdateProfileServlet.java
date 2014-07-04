package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class UpdateProfileServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateProfileServlet get");
		
		HttpSession session = request.getSession();
		String notesID = (String)session.getAttribute("NotesID");
		System.out.println(notesID);
		try {
			EntityProfile profile = db.getProfile(notesID);
			if (profile != null) {
				System.out.printf("Name:%s", profile.getName());
				System.out.printf("getPemID:%s", profile.getPemID());
				System.out.printf("getIlID:%s", profile.getIlID());
				System.out.printf("getTechDomain:%s", profile.getTechDomain());
				System.out.printf("getTechOther:%s", profile.getTechOther());
				System.out.println(profile.getUtilization());
				System.out.printf("getLocation:%s", profile.getLocation());
				System.out.printf("getOnSiteFlag:%s", profile.getOnSiteFlag());
				System.out.printf("getOnBenchFlag:%s", profile.getOnBenchFlag());
				
				request.setAttribute("profile", profile);
				request.getRequestDispatcher("/profile.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateProfileServlet post");
		
		try {
			HttpSession session = request.getSession(true);
			String notesID = (String) session.getAttribute("NotesID");
			System.out.printf("NotesID:%s", notesID);

			String pemID = "";
			if (request.getParameter("selectPeM") != null) {
				pemID = request.getParameter("selectPeM");
			}
			System.out.printf("pemID:%s", pemID);

			String ilID = "";
			if (request.getParameter("IL") != null) {
				ilID = request.getParameter("IL");
			}
			System.out.printf("ilID:%s", ilID);
			
			String techDomain = request.getParameter("selectDomain");
			System.out.printf("TechDomain:%s", techDomain);
			String techOther = request.getParameter("txtDomain");
			
			double utilization = 0;
			if (request.getParameter("Utilization") != null && !request.getParameter("Utilization").equals("")) {
				utilization = Double.parseDouble(request.getParameter("Utilization"));
			}
			
			String location = request.getParameter("Location");
			String onSiteFlag = "0";
			if (request.getParameter("chkCustomer") != null
					&& request.getParameter("chkCustomer").equals("on")) {
				onSiteFlag = "1";
			}

			String onBenchFlag = "0";
			if (request.getParameter("chkOnBench") != null
					&& request.getParameter("chkOnBench").equals("on")) {
				onBenchFlag = "1";
			}

			String regiesteredFlag = "0";
			if (request.getParameter("chkRegiestered") != null
					&& request.getParameter("chkRegiestered").equals("on")) {
				regiesteredFlag = "1";
			}
			
			int rows = db.updateProfile(notesID, pemID, ilID, 
					techDomain, techOther, utilization, 
					location, onSiteFlag, onBenchFlag, regiesteredFlag);

			System.out.printf("%d", rows);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		response.sendRedirect("/index.jsp");
	}
}
