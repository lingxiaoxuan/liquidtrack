<%@page import="com.ibm.bluemix.samples.EntityTrack"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Track</title>
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
<body>
 <form action="/updateTrack" method="post" name="form1">
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
		
<table class="table">
	<% EntityTrack track = (EntityTrack)request.getAttribute("track"); %>
	<tr>
		<td>Liquid ID:</td>
		<td>
			<input type="text" name="LiquidID" readonly value=<%=track.getLiquidID() %> >
		</td>
	</tr>
	<tr>
		<td>Liquid Name:</td>
		<td><input type="text" name="LiquidName" readonly value=<%=track.getName() %> ></td>
	</tr>
	<tr>
		<td>Technology:</td>
		<td>
			<select id="selectDomain" name="selectDomain" disabled>
				<option id="selectDomain1" value="1">Web</option>
				<option id="selectDomain2" value="2">MF</option>
				<option id="selectDomain3" value="3">Mobile</option>
				<option id="selectDomain4" value="4">Others</option>
			</select>
			<input type="text" name="txtDomain" readonly value=<%=track.getTechOther() %>>
		</td>
	</tr>
	<tr>
		<td>Event Type:</td>
		<td>
			<select id="selectEvent" name="selectEvent" disabled>
				<option id="selectEvent1" value="1">Basic Task</option>
				<option id="selectEvent2" value="2">Component Design</option>
				<option id="selectEvent3" value="3">Component Development</option>
				<option id="selectEvent4" value="4">Test</option>
				<option id="selectEvent5" value="5">Others</option>
			</select>
			<input type="text" name="txtEvent" readonly value=<%=track.getEventOther() %>>
		</td>
	</tr>
	<tr>
		<td>Register Date:</td>
		<td>
			<div id="datetimepicker1" class="input-append date">
		      <input type="text" name="RegisterDate" readonly value=<%=track.getRegisterDate() %>></input>
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
		      $('#datetimepicker1').enable = false;
		    </script>
		</td>
	</tr>
		  		<tr>
		  			<td>Complete Date:</td>
		  			<td>
		  				<div id="datetimepicker2" class="input-append date">
					      <input type="text" name="CompleteDate" readonly value=<%=track.getCompleteDate() %> ></input>
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
							<option id="selectStatus1" value="1">In Progress</option>
							<option id="selectStatus2" value="2">Complete</option>
						</select>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>1st Win:</td>
		  			<td><input id="ckFirst" type="checkbox" name="FirstFlag" onclick="F2()"></td>
		  		</tr>
		  		<tr>
		  			<td>2nd Win:</td>
		  			<td><input id="ckSecond" type="checkbox" name="SecondFlag" onclick="F3()"></td>
		  		</tr>
		  		<tr>
		  			<td>Win Dollar:</td>
		  			<td><span class="add-on">$</span><input type="text" id="winDollar" name="Dollar" value=<%=track.getWinDollar() %> style="ime-mode:disabled" onkeyup= "value=value.replace(/[^\d]/g, '')   "onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "></td>
		  		</tr>
		  		<tr>
		  			<td>Win Hour:</td>
		  			<td><input type="text" id="winHour" name="Hour" value=<%=track.getWinHour() %>  style="ime-mode:disabled" onkeyup= "value=value.replace(/[^\d]/g, '')   "onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "></td>
		  		</tr>
		  		<tr>
		  			<td>Win Point:</td>
		  			<td><input type="text" id="winPoint" name="Point" value=<%=track.getWinPoint() %> style="ime-mode:disabled" onkeyup= "value=value.replace(/[^\d]/g, '')   "onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "></td>
		  		</tr>
	  		</table>
	<script>
		document.getElementById("selectDomain" + <%= track.getTechDomain() %>).selected = true;
		document.getElementById("selectEvent" + <%= track.getEventType() %>).selected = true;
		document.getElementById("selectStatus" + <%= track.getStatus() %>).selected = true;
		<% if (track.getIsFirst().equals("1")) { %>
		document.getElementById("ckFirst").checked = <%= track.getIsFirst() %>;
		document.getElementById("winDollar").value = <%=track.getWinDollar() %>;
		document.getElementById("winHour").value = <%=track.getWinHour() %>;
		document.getElementById("winPoint").value = <%=track.getWinPoint() %>;
		<% } else { %>
		document.getElementById("ckSecond").checked = <%= track.getIsSecond() %>;
		document.getElementById("winPoint").value = <%=track.getWinPoint() %>;
		<% }%>
	</script>
	
	<input type="submit" class="btn btn-primary" value="Update">
</form>
</body>
</html>