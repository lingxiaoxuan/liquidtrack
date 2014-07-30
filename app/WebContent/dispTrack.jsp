<%@page import="com.ibm.bluemix.samples.EntityTrack"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Hisory</title>
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
        padding-top: 70px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }
  </style>
  <script src="/js/bootstrap.min.js"></script>
  <script src="/js/jquery-2.0.3.min.js"></script>
  <script type="text/javascript">
		function updateClick() {
			var count = 0;
			var ckId = "";
			$('input[type="checkbox"][name="ckCheck"]:checked').each(function() {
				count ++;
				ckId = $(this).attr("id");
			});
			
			if (count != 1) {
				alert("count is " + count + ", please only choose one");
				return false;
			}

			var notesID = ckId.split('_')[0];
			var liquidID = ckId.split('_')[1];

			if (notesID == "" || liquidID == "") {
				return false;
			}
			window.location.href="/updateTrack?NotesID=" + notesID + "&LiquidID=" + liquidID; 
		};
		
		function deleteClick() {
			var count = 0;
			var NotesIDList = "";
			$('input[type="checkbox"][name="ckCheck"]:checked').each(function() {
				count ++;
				NotesIDList += $(this).attr("id");
				NotesIDList += ",";
			});
			
			if (count < 1) {
				return false;
			}
			
			var con = confirm("these tracks will be deleted, are you sure?");
			if (con) {
				$.post("/deleteTrack", {"NotesIDList":NotesIDList}, function(result) {
					window.location.href="/dispTrack";
				});			
			}
		}
		
		function downloadClick()
		{
			window.location.href="/downloadTrack"; 
		}
  </script>
</head>
<meta charset="utf-8">
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
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
</div>
 	
    <div class="container">
	    
    </div>
    
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <img src="/img/pic5.jpg">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Information Collection</li>
              <li class="active"><a href="#">Record</a></li>
              <li><a href="#">Contact us</a></li>
              <li><a href="#">Download</a></li>
              <li><a href="#">Upload</a></li>
              <li><a href="#">Notes</a></li>
              <li class="nav-header">Other</li>
              <li><a href="#">More...</a></li>
              <li><a href="#">More...</a></li>
              <li><a href="#">More...</a></li>
              <li><a href="#">More...</a></li>
              <li><a href="#">More...</a></li>
              <li class="nav-header">Sidebar</li>
              <li><a href="#">More...</a></li>
              <li><a href="#">More...</a></li>
              <li><a href="#">More...</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
    
		<div class="span9">
			<div class="hero-unit">
	            <h1>Information</h1>
	            <p>General users can only see their own information.The administrator can browse all the user's information and download.</p>
	            <p><form class="form-search">
				    <input type="text" placeholder="Notes ID" class="span2 search-query">
				    <button type="submit" class="btn">Search</button>
			    </form>
	        </div>

		  	<div class="row-fluid">
		  		<div class="span12">
		  		<table class="table table-striped" id="trackForm">
		  			<tr>
			  			<th></th>
			  			<th>Notes ID</th>
			  			<th>LiquidID</th>
			  			<th>Name</th>
			  			<th>Technology</th>
			  			<th>Register Date</th>
			  			<th>Complete Date</th>
			  			<th>Status</th>
			  			<th>1st Win</th>
			  			<th>2nd Win</th>
			  			<th>Win Dollar</th>
			  			<th>Win Hour</th>
			  			<th>Win Point</th>
			  		</tr>
			  		
		<% 
         java.util.List<EntityTrack> trackList = (java.util.List<EntityTrack>) request.getAttribute("trackList");
         if (trackList != null) {
        	 for (EntityTrack track : trackList) {
        		 %>
     		        <tr>
			  			<td><input type="checkbox" name="ckCheck" id="<%=track.getNotesID() + "_" + track.getLiquidID() %>"></td>
			  			<td><%= track.getNotesID() %></td>
			  			<td><%= track.getLiquidID() %></td>
			  			<td><%= track.getName() %></td>
			  			<td><%= track.getTechDisp() %></td>
			  			<td><%= track.getRegisterDate() %></td>
			  			<td><%= track.getCompleteDate() %></td>
			  			<td><%= track.getStatusDisp() %></td>
			  			<td><%= track.geIsFirstDisp() %></td>
			  			<td><%= track.getIsSecondDisp() %></td>
			  			<td><%= track.getWinDollar() %></td>
			  			<td><%= track.getWinHour() %></td>
			  			<td><%= track.getWinPoint() %></td>
       				</tr>
        		 <%
        	 }
         }
		%>
		  		</table>
		  		</div>
		  	</div>
		  	<%if ("1000".equals(session.getAttribute("RoleID"))){ %>
		  		<input type="submit" class="btn btn-primary" value="Download" onclick="downloadClick();" />
		  	<%} %>
		  	<input type="button" class="btn btn-primary" value="Update" onclick="updateClick();"/>
		  	<input type="button" class="btn btn-primary" value="Delete" onclick="deleteClick();"/>
		</div>
	</div>
	
	 	<footer>
	        <p class="pull-right"><a href="#">Back to top</a></p>
	     </footer>
	</div>

</body>
<<<<<<< HEAD
</html>
=======
</html>
>>>>>>> origin/master
