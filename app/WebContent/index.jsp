<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Liquid Track System</title>
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
			  <li><a href="login.jsp">Logout</a></li>
	         </ul>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div id="myCarousel" class="carousel slide">
      <div class="carousel-inner">
        <div class="item active">
          <img src="/img/pic1.jpg">
          <div class="container">
            <div class="carousel-caption">
              <h1>Hey Guys.</h1>
              <p class="lead">Welcome to our web and enjoy it.</p>
              <p class="lead">The dream of us, the heartbeat of millions.</p>
              <p class="lead">Inside our hearts, the passion of a champion.</p>
              <a class="btn btn-large btn-primary" href="addTrack.jsp">Add Track</a>
            </div>
          </div>
        </div>
    </div>
    </div>
    
    <footer class="form-footer">
       <p><a href="index.jsp">home</a> | <a href="addTrack.jsp">Add</a> | <a href="/updateProfile">Profile</a> | <a href="/dispTrack">History</a>| <a href="login.jsp">Logout</a></p>
       <p>&copy; 2014 Information Collection. All Rights Reserved. | <a href="#">GDC</a></p>
     </footer>
</body>
</html>