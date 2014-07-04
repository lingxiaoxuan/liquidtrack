<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login</title>
  <meta charset="utf-8">
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/style.css" rel="stylesheet">	
  <link href="http://www.see-source.com/bootstrap/css/bootstrap.css" rel="stylesheet">  
  <script src="/js/jquery-2.0.3.min.js"></script>
  <script src="/js/bootstrap-carousel.js"></script>
  <script src="/js/bootstrap-modal.js"></script>
  <script src="/js/bootstrap-transition.js"></script>
  <script src="/js/jquery.js"></script>
<style type="text/css">
  <style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }
  </style>
  <script src="/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<form class="form-signin" action="/login" method="get" name="form1">
	<script type="text/javascript"> 
	 window.history.forward(1);
		function F1()
		{
			window.location.href="register.jsp"; 
		}
		
		function F2()
		{
			form1.NotesID.value="";
			form1.pwd.value="";
		}
		
		$('#myCarousel').carousel('next');
	</script>
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" class="input-block-level" placeholder="E-mail ID" name="NotesID" id="NotesID" required>
    <input type="password" class="input-block-level" placeholder="Password" name="pwd" required>
    
    <% if (request.getAttribute("errMsg") != null) {%>
    	<div class="alert alert-info">
    <%
    	out.println(request.getAttribute("errMsg"));%>
    	</div>
    <%} %>
    <button class="btn btn-large btn-primary" type="submit">Sign in</button>
    <button type="button" class="btn btn-large btn" onclick="F2()">Cancel</button>
    <input type="button" class="btn btn-link" onclick="F1()" value="Register">
</form>
</div>

<div id="myCarousel" class="carousel slide">
	<ol class="carousel-indicators">  
       <li data-target="#myCarousel" data-slide-to="0"  class="" ></li>  
       <li data-target="#myCarousel" data-slide-to="1" class=""></li>  
       <li data-target="#myCarousel" data-slide-to="2" class="active"></li>  
    </ol>  
    
     <div class="carousel-inner">  
          <div class="item">  
             <img src="/img/pic1.jpg">
          </div>  
          <div class="item">  
             <img src="/img/pic2.jpg">  
          </div>  
          <div class="item active">  
             <img src="/img/pic3.jpg">
             <div style="position:absolute; z-index:2; left:80px; top:100px">
             	<div class="container">
	              <h1>Forget Password.</h1>
	              <p class="lead">Please contact us and fetch your password.</p>
	              <a class="btn btn-large btn-primary" href="#Contact"  data-toggle="modal" backdrop="false" data-backdrop="static">Contact us</a>
	            </div>
	         </div>
          </div>  
      </div>  
     <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
     <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
   </div>
	
	<div id="Contact" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-header">
	   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	   		<h3 id="myModalLabel">Contact</h3>
   	  </div>
	  <div class="modal-body">
		  <p>E-mail ID:</p>
  	  </div>
 	  <div class="modal-footer">
  		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  	  </div>
    </div>	
</body>
</html>