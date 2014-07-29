<%@page import="com.ibm.bluemix.samples.EntityProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Admin</title>
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/style.css" rel="stylesheet">	
<script src="/js/jquery-2.0.3.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
</head>
<meta charset="utf-8">
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
	  <div class="navbar-inner">
	    <div class="container">
	      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="brand" href="#">Welcome <%=session.getAttribute("NotesID") %></a>
	      <div class="nav-collapse collapse">
	        <ul class="nav">
	          <li><a href="index.jsp">Home</a></li>
	          <li><a href="addTrack.jsp">Add</a></li>
	          <li><a href="/updateProfile">Profile</a></li>
	          <li><a href="/dispTrack">History</a></li>
	          <%if ("1000".equals(session.getAttribute("RoleID"))){ %>
	          <li><a href="admin.jsp">Admin</a></li>
	          <%} %>
			  <li><a href="login.jsp">Logout</a></li>
	         </ul>
	      </div>
	    </div>
	  </div>
	</div>
	
	<form action="/searchPW" method="get" name="form1">
		<input type="text" class="input-block-level" placeholder="E-mail ID" name="NotesID" id="NotesID" required>
		<button class="btn btn-large btn-primary" type="submit">Search</button>
		<span class="add-on"><%=request.getAttribute("password") %></span>
	</form>
</body>
</html>