package com.ibm.bluemix.samples;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class DispTrackServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DispTrack Servlet");
		
		try {
			HttpSession session = request.getSession(true);
			String notesID = (String)session.getAttribute("NotesID");
			String roleID = (String)session.getAttribute("RoleID");
						
			if (!notesID.equals("") && !roleID.equals("")) {
				List<EntityTrack> trackList = db.getTrackList(notesID, roleID);
				
				if (trackList != null && trackList.size() > 0) {
					request.setAttribute("trackList", trackList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/dispTrack.jsp").forward(request, response);
	}
}
