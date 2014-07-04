<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Add Track</title>
  <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="screen" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
	<link href="/css/style.css" rel="stylesheet">	
  <style>
    body {
        padding-top: 70px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }
  </style>
  <script src="/js/bootstrap.min.js"></script>
</head>
<meta charset="utf-8">
<body>
  <form action="/addTrack" method="post" name="form1">
  	<script src="/js/jquery.js"></script>
  	<script src="/js/bootstrap-dropdown.js"></script>
  	<script src="/js/jquery-2.0.3.min.js"></script>
  	<script src="/js/bootstrap.min.js"></script>
  	<script src="/js/bootstrap-datetimepicker.min.js"></script>
  	<script type="text/javascript"> 
	 	window.history.forward(1);
		function F1()
		{
			//when "other"
			if (form1.selectEvent.value == "5")
			{
				form1.txtEvent.disabled = false;
			}
			else
			{
				form1.txtEvent.value = "";
				form1.txtEvent.disabled = true;
			}
		}

		function F2()
		{
			//when 1st
			if (form1.FirstFlag.checked)
			{
				form1.Dollar.disabled = false;
				form1.Hour.disabled = false;
				form1.Point.disabled = false;
				form1.SecondFlag.checked = false;
			}
			else
			{
				form1.Dollar.value = "";
				form1.Dollar.disabled = true;
				form1.Hour.value = "";
				form1.Hour.disabled = true;
				form1.Point.value = "";
				form1.Point.disabled = true;
			}
		}
		
		function F3()
		{
			//when 2nd
			if (form1.SecondFlag.checked)
			{
				form1.FirstFlag.checked = false;
				form1.Dollar.value = "";
				form1.Dollar.disabled = true;
				form1.Hour.value = "";
				form1.Hour.disabled = true;
				form1.Point.disabled = false;
				form1.Point.disabled = false;
			}
			else
			{
				form1.Point.value = "";
				form1.Point.disabled = true;
			}
		}
		function F4()
		{
			//when "other"
			if (form1.selectDomain.value == "4")
			{
				form1.txtDomain.disabled = false;
			}
			else
			{
				form1.txtDomain.value = "";
				form1.txtDomain.disabled = true;
			}
		}
		!function ($) {
	        $(function(){
	          $('#myCarousel').carousel()
	        })
	      }(window.jQuery)
	</script>
  
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
  
	  <div id="myCarousel" class="container">
	  	<div>
	  		<table class="table">
	  			<tr>
		  			<td>Liquid ID:</td>
		  			<td>
		  				<input type="text" name="LiquidID" required>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>Liquid Name:</td>
		  			<td><input type="text" name="LiquidName"></td>
		  		</tr>
		  		<tr>
		  			<td>Technology:</td>
		  			<td>
		  				<select id="selectDomain" name="selectDomain" onchange="F4()">
							<option value="1">Web</option>
							<option value="2">MF</option>
							<option value="3">Mobile</option>
							<option value="4">Others</option>
						</select>
		  				<input type="text" name="txtDomain" disabled="true">
		  			</td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Event Type:</td>
		  			<td>
		  				<select id="selectEvent" name="selectEvent" onchange="F1()">
							<option value="1">Basic Task</option>
							<option value="2">Component Design</option>
							<option value="3">Component Development</option>
							<option value="4">Test</option>
							<option value="5">Others</option>
						</select>
		  				<input type="text" name="txtEvent" disabled="true">
		  				<span style="color:red">The Liquid Event should copy from Liquid Portal</span>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>Register Date:</td>
		  			<td>
		  				<div id="datetimepicker1" class="input-append date">
					      <input type="text" name="RegisterDate" readonly></input>
					      <span class="add-on">
					        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
					      </span>
					    </div>
					    <script type="text/javascript">
					      $('#datetimepicker1').datetimepicker({
					        format: 'MM/dd/yyyy',
					        language: 'en',
					        pickDate: true,
					        hourStep: 1,
					        minuteStep: 15,
					        secondStep: 30,
					        inputMask: true
					      });
					    </script>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>Complete Date:</td>
		  			<td>
		  				<div id="datetimepicker2" class="input-append date">
					      <input type="text" name="CompleteDate" readonly></input>
					      <span class="add-on">
					        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
					      </span>
					    </div>
					    <script type="text/javascript">
					      $('#datetimepicker2').datetimepicker({
					        format: 'MM/dd/yyyy',
					        language: 'en',
					        pickDate: true,
					        inputMask: true
					      });
					    </script>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>Status:</td>
		  			<td>
		  				<select id="selectStatus" name="selectStatus">
							<option value="1">In Progress</option>
							<option value="2">Complete</option>
						</select>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>1st Win:</td>
		  			<td><input type="checkbox" name="FirstFlag" onclick="F2()"></td>
		  		</tr>
		  		<tr>
		  			<td>2nd Win:</td>
		  			<td><input type="checkbox" name="SecondFlag" onclick="F3()"></td>
		  		</tr>
		  		<tr>
		  			<td>Win Dollar:</td>
		  			<td><span class="add-on">$</span><input type="text" name="Dollar" disabled="true" style="ime-mode:disabled" onkeyup= "value=value.replace(/[^\d]/g, '')   "onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "></td>
		  		</tr>
		  		<tr>
		  			<td>Win Hour:</td>
		  			<td><input type="text" name="Hour" disabled="true" style="ime-mode:disabled" onkeyup= "value=value.replace(/[^\d]/g, '')   "onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "></td>
		  		</tr>
		  		<tr>
		  			<td>Win Point:</td>
		  			<td><input type="text" name="Point" disabled="true" style="ime-mode:disabled" onkeyup= "value=value.replace(/[^\d]/g, '')   "onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "></td>
		  		</tr>
	  		</table>
	  		<table class="table">
	  			<tr><td><input type="submit" class="btn btn-primary" value="Add" /></td></tr>
	  		</table>
	  	</div>
	  	
	  	 <footer>
	        <p class="pull-right"><a href="#">Back to top</a></p>
	     </footer>
	  </div>
  </form>
</body>
</html>
