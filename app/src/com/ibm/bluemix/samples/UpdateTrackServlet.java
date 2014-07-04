package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class UpdateTrackServlet extends HttpServlet{
	private PostgreSQLClient db = new PostgreSQLClient();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateTrackServlet");
		String notesID = request.getParameter("NotesID");
		String liquidID = request.getParameter("LiquidID");
		HttpSession session = request.getSession();
		session.setAttribute("TrueNotesID", notesID);
		session.setAttribute("LiquidID", liquidID);

		try {
			EntityTrack track = db.getTrack(notesID, liquidID);
			if (track == null) {
				response.sendRedirect("/dispTrack");
			}
			request.setAttribute("track", track);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/updateTrack.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateTrackServlet post");
		try {
			HttpSession session = request.getSession(true);
			String notesID = (String) session.getAttribute("TrueNotesID");
			String liquidID = (String) session.getAttribute("LiquidID");
			System.out.printf("NotesID:%s;LiquidID:%s", notesID, liquidID);

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
			
			System.out.println(registerDate);
			System.out.println(completeDate);
			System.out.println(status);
			System.out.println(isFirst);
			System.out.println(isSecond);
			System.out.println(winDollar);
			int rows = db.updateTrack(notesID, liquidID, completeDate, status, isFirst, isSecond, winDollar, winHour, winPoint);

			System.out.printf("%d", rows);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		response.sendRedirect("/dispTrack");
	}
}
