<%@page import="com.ibm.bluemix.samples.EntityProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Profile</title>
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/style.css" rel="stylesheet">	
  <script src="/js/jquery-2.0.3.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  <script src="/js/info.js"></script>
  <style>
    body {
    	padding-top: 70px;
        padding-bottom: 40px;
        background-color: #9ACD32;
      }
  </style>
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
	
	<% if ("0".equals((String)session.getAttribute("RegiesteredFlag"))) {
	%>
		<div class="carousel-inner">  
          <div class="item active">  
            <div class="container">
              <h1>About register.</h1>
              <p class="lead">
              	Your profile shows that you are still not register at Liquid Portal. Please register at Liquid Portal first, and update the profile info as registered.
              	<a class="btn btn-large btn-link" href="https://w3-connections.ibm.com/communities/service/html/communityview?communityUuid=eebba8fa-fc09-4465-8916-5e4542c44951#fullpageWidgetId=Waa361913e535_42f1_8ef3_c6a090f53917&file=40c93b05-787e-440a-a1e9-214a97208f21"  >register guide</a>
              </p>
            </div>
          </div>  
       </div>  
	<%
	} %>
	
	<form action="/updateProfile" method="post" name="form1" onsubmit="return checkform();">
	<% EntityProfile profile = (EntityProfile)request.getAttribute("profile"); %>
	
	<script type="text/javascript"> 
	 	window.history.forward(1);
		
		function F1()
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
	</script>
	
	<div class="form-profile">
		<div class="row-fluid">
			<div class="span12">
				<table class="table">
	  			<tr>
		  			<td>E-mail ID:</td>
		  			<td><input type="text" name="NotesID" onblur="chkNotesID();" readonly required value=<%=profile.getNotesID() %> ><span class="add-on"></span></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Name:</td>
		  			<td><input type="text" name="Name" readonly value=<%=profile.getName() %> ></td>
		  			<td></td>
		  		</tr>

		  		<tr>
		  			<td>PeM:</td>
		  			<td>
		  				<select id="selectPeM" name="selectPeM">
							<option id="selectPeM1" value="1">Sun Yan Feng</option>
							<option id="selectPeM2" value="2">Gu Jiang</option>
							<option id="selectPeM3" value="3">Wu Yang</option>
						</select>
		  			</td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>IL:</td>
		  			<td><input type="text" name="IL" value="<%=profile.getIlID() %>" ></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Tech Domain:</td>
		  			<td>
		  				<select id="selectDomain" name="selectDomain" onchange="F1()">
							<option id="selectDomain1" value="1">Web</option>
							<option id="selectDomain2" value="2">MF</option>
							<option id="selectDomain3" value="3">Mobile</option>
							<option id="selectDomain4" value="4">Others</option>
						</select>
		  				<input type="text" name="txtDomain" value="<%= profile.getTechOther() %>" disabled="true">
		  			</td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Current Utilization:</td>
		  			<td><input type="text" name="Utilization" style="ime-mode:disabled" value="<%=profile.getUtilization() %>" oninput="chkNum(this);" required></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Current Location:</td>
		  			<td><input type="text" name="Location" value="<%=profile.getLocation() %>"></td>
		  			<td>(eg:Shanghai,Shenzhen...)</td>
		  		</tr>
		  		<tr>
		  			<td>Is Working at Customer site:</td>
		  			<td><input type="checkbox" name="chkCustomer" id="chkCustomer" ></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Is OnBench:</td>
		  			<td><input type="checkbox" name="chkOnBench" id="chkOnBench" ></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Is Regiestered:<a target="_blank" href="https://w3-connections.ibm.com/communities/service/html/communityview?communityUuid=eebba8fa-fc09-4465-8916-5e4542c44951#fullpageWidgetId=Waa361913e535_42f1_8ef3_c6a090f53917&file=40c93b05-787e-440a-a1e9-214a97208f21">register guide</a></td>
		  			<td><input type="checkbox" name="chkRegiestered" id="chkRegiestered" ></td>
		  			<td></td>
		  		</tr>
	
	  			<tr>
	  				<td><input type="submit" class="btn btn-primary" value="Update" /></td>
		  			<td></td>
		  		</tr>
	  		</table>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	document.getElementById("selectPeM" + "<%=profile.getPemID()%>").selected = true;
	document.getElementById("selectDomain" + <%= profile.getTechDomain() %>).selected = true;
	document.getElementById("chkCustomer").checked = <%=profile.getOnSiteFlag() %>;
	document.getElementById("chkOnBench").checked = <%=profile.getOnBenchFlag() %>;
	document.getElementById("chkRegiestered").checked = <%=profile.getRegiesteredFlag() %>;
	</script>
	<footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
     </footer>
	</form>
</body>
</html>