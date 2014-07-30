package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteTrackServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteTrackServlet post");
		
		try {
			String notesIDList = request.getParameter("NotesIDList");
			System.out.printf("notesIDList:%s", notesIDList);

			if (notesIDList != null && !("").equals(notesIDList)) {
				String[] strList = notesIDList.split(",");
				String notesID = "";
				String liquidID = "";
				int rows = 0;
				for (String str : strList) {
					if (!("").equals(str)) {
						notesID = str.split("_")[0];
						liquidID = str.split("_")[1];
						if (liquidID != null && !"".equals(liquidID)) {
							rows += db.deleteTrack(notesID, liquidID);
						}
					}
				}
				System.out.printf("%d", rows);
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
<<<<<<< HEAD
}
=======
}

>>>>>>> origin/master
