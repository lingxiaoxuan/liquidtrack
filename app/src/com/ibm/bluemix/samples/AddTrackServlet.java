package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class AddTrackServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddTrackServlet");
		try {
			HttpSession session = request.getSession(true);
			String notesID = (String) session.getAttribute("NotesID");
			System.out.printf("NotesID:%s", notesID);
			String liquidID = request.getParameter("LiquidID");
			String name = new String(request.getParameter("LiquidName").getBytes("ISO-8859-1"), "UTF-8");
			String techDomain = request.getParameter("selectDomain");
			String techOther = "";
			if (techDomain != "4" && request.getParameter("txtDomain") != null) {
				techOther = request.getParameter("txtDomain");
			}

			String eventType = request.getParameter("selectEvent");
			String eventOther = "";
			if (eventType != "4" && request.getParameter("txtEvent") != null) {
				eventOther = request.getParameter("txtEvent");
			}

			String registerDate = "";
			if (request.getParameter("RegisterDate") != null
					&& !request.getParameter("RegisterDate").equals("")) {
				registerDate = request.getParameter("RegisterDate");
			}
			System.out.printf("registerDate:%s", registerDate);

			String completeDate = request.getParameter("CompleteDate");
			if (request.getParameter("CompleteDate") != null
					&& !request.getParameter("CompleteDate").equals("")) {
				completeDate = request.getParameter("CompleteDate");
			}
			System.out.printf("completeDate:%s", completeDate);
			String status = request.getParameter("selectStatus");
			String isFirst = "0";
			if (request.getParameter("FirstFlag") != null
					&& request.getParameter("FirstFlag").equals("on")) {
				isFirst = "1";
			}

			String isSecond = "0";
			if (request.getParameter("SecondFlag") != null
					&& request.getParameter("SecondFlag").equals("on")) {
				isSecond = "1";
			}

			double winDollar = 0;
			double winHour = 0;
			double winPoint = 0;
			if (isFirst.equals("1")) {
				if (request.getParameter("Dollar") != null
						&& !request.getParameter("Dollar").equals("")) {
					winDollar = Double.parseDouble(request
							.getParameter("Dollar"));
				}
				if (request.getParameter("Hour") != null
						&& !request.getParameter("Hour").equals("")) {
					winHour = Double.parseDouble(request.getParameter("Hour"));
				}
			}

			if (isSecond.equals("1") || isFirst.equals("1")) {
				if (request.getParameter("Point") != null
						&& !request.getParameter("Point").equals("")) {
					winPoint = Double
							.parseDouble(request.getParameter("Point"));
				}
			}
			System.out.println("begin");
			System.out.println(notesID + "1");
			System.out.println(liquidID + "1");
			System.out.println(name + "1");
			System.out.println(techDomain + "1");
			System.out.println(techOther + "1");
			System.out.println(eventType + "1");
			System.out.println(eventOther + "1");
			System.out.println(registerDate + "1");
			System.out.println(completeDate + "1");
			System.out.println(status + "1");
			System.out.println(isFirst + "1");
			System.out.println(isSecond + "1");
			System.out.println(winDollar + "1");
			int rows = db.addTrack(notesID, liquidID, name, techDomain,
					techOther, eventType, eventOther, registerDate,
					completeDate, status, isFirst, isSecond, winDollar,
					winHour, winPoint);

			System.out.printf("%d", rows);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		response.sendRedirect("/dispTrack");
	}
}
