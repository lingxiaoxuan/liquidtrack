package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("login Servlet");
    	
    	String errMsg = "";
    	String notesId = request.getParameter("NotesID");
    	String pwd = request.getParameter("pwd");
    	System.out.println(notesId);
    	System.out.println(pwd);
    	try {
			EntityProfile profile = db.getProfile(notesId);
			if (profile != null && profile.getPassword() != null) {
				String pass = profile.getPassword();
				if (pass.equals(pwd)) {
					HttpSession session = request.getSession(true);
					session.setAttribute("NotesID", notesId);
					session.setAttribute("RoleID", profile.getRoleID());
					session.setAttribute("RegiesteredFlag", profile.getRegiesteredFlag());
					System.out.printf("NotesID:%s;RoleID:%s;RegiesteredFlag:%s", notesId, profile.getRoleID(), profile.getRegiesteredFlag());
			    	response.setContentType("text/html; charset=UTF-8");
			        response.setStatus(200);
			        
					if ("0".equals(profile.getRegiesteredFlag())) {
						response.sendRedirect("/updateProfile");
						return;
					}
					response.sendRedirect("/index.jsp");
					return;
				}
				else {
					errMsg = "Password is wrong, please re-enter";
				}
			}
			else {
				errMsg = "NotesId is not exist, please re-enter";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.printf("errMsg:%s", errMsg);
        if (!errMsg.equals("")) {
        	request.setAttribute("errMsg", errMsg);
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
