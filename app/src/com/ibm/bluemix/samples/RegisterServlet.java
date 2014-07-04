package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("register Servlet");

		try {
			// Insert Data
			String notesID = request.getParameter("NotesID");
			System.out.println(notesID);
			String password = request.getParameter("Password");
			System.out.println(password);
			String name = request.getParameter("Name");
			System.out.println(name);
			String pemID = request.getParameter("selectPeM");
			String ilID = request.getParameter("IL");
			String domain = request.getParameter("selectDomain");
			System.out.printf("domain:%s", domain);

			String domainTxt = "";
			if (domain.equals("4")) {
				System.out.printf("domainTxt:%s", domainTxt);
				if (request.getParameter("txtDomain") != null) {
					domainTxt = request.getParameter("txtDomain");
				}
			}

			double utilization = 0;
			if (request.getParameter("Utilization") != null && !request.getParameter("Utilization").equals("")) {
				utilization = Double.parseDouble(request.getParameter("Utilization"));
			}
			System.out.println(utilization);
			
			String location = request.getParameter("Location");
			System.out.println(location);

			String onSiteFlag = "0";
			if (request.getParameter("chkCustomer") != null && request.getParameter("chkCustomer").equals("on")) {
				onSiteFlag = "1";
			}

			String onBenchFlag = "0";
			if (request.getParameter("chkOnBench") != null && request.getParameter("chkOnBench").equals("on")) {
				onBenchFlag = "1";
			}

			String regiesteredFlag = "0";
			if (request.getParameter("chkRegiestered") != null && request.getParameter("chkRegiestered").equals("on")) {
				regiesteredFlag = "1";
			}

			int rows = db.addProfile(notesID, password, name, pemID, ilID, domain,
					domainTxt, utilization, location, onSiteFlag,
					onBenchFlag, regiesteredFlag);
			System.out.printf("%d", rows);
			String msg = "Added " + rows + " rows.";
			request.setAttribute("msg", msg);

			HttpSession session = request.getSession(true);
			session.setAttribute("NotesID", notesID);
			if (notesID.equals("notes@cn.ibm.com")) {
				session.setAttribute("RoleID", "1000");
			}
			else {
				session.setAttribute("RoleID", "1001");
			}
			session.setAttribute("RegiesteredFlag", regiesteredFlag);
			
			if ("0".equals(regiesteredFlag)) {
				response.sendRedirect("/updateProfile");
				return;
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace(System.err);
		}
		
		response.sendRedirect("/index.jsp");
	}
}
