<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Registion</title>
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }
  </style>
  <script src="/js/bootstrap.min.js"></script>
  <script src="/js/jquery-2.0.3.min.js"></script>
  <script src="/js/jqBootstrapValidation.js"></script>
  <script src="/js/bootstrap-validation.min.js"></script>
  <script src="/js/info.js"></script>
</head>
<meta charset="utf-8">
<body>
	<form action="/register" method="post" name="form1" onsubmit="return checkform();">
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
		function checkPasswords() {  
            var passl = document.getElementById("Password");  
            var pass2 = document.getElementById("pwdCm");  
            if (passl.value != "" && pass2.value != "")
            {
            	if (passl.value != pass2.value)
                {
            		document.getElementById("pwdCMdiv").style.visibility="visible"; 
                }
                else 
                {
                	document.getElementById("pwdCMdiv").style.visibility="hidden";
                }
            }
        } 
		function F2()
		{
			window.location.href="login.jsp"; 
		}
	</script>
	  <div class="container">
	  	<div>
	  		<h2 class="form-signin-heading">Regestion</h2>
	  		<table class="table">
	  			<tr>
		  			<td>E-mail ID:</td>
		  			<td><input id="notesID" type="text" name="NotesID" onblur="chkNotesID();" required><span id="notesIDInfo" class="add-on"></span></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Name:</td>
		  			<td><input type="text" name="Name"></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Password:</td>
		  			<td><input type="password" name="Password" id="Password" required></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Password Confirm:</td>
		  			<td>
		  				<input type="password" name="pwdCm" id="pwdCm" required  oninput="checkPasswords()" >
		  			</td>
		  			<td>
		  				<div id="pwdCMdiv" class="alert alert-info" style= "visibility:hidden; ">Passwords do not match!</div>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>PeM:</td>
		  			<td>
		  				<select id="selectPeM" name="selectPeM">
							<option value="1">Sun Yan Feng</option>
							<option value="2">Gu Jiang</option>
							<option value="3">Wu Yang</option>
						</select>
		  			</td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>IL:</td>
		  			<td><input type="text" name="IL" value="Sun Yan Feng"></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Tech Domain:</td>
		  			<td>
		  				<select id="selectDomain" name="selectDomain" onchange="F1()">
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
		  			<td>Current Utilization:</td>
		  			<td><input type="text" id="Utilization" name="Utilization" style="ime-mode:disabled" oninput="chkNum(this);" required></td>
		  			<td><label id="utInfo" style="color:red;display:none">Utilization is invalid,please re-enter!</label>(eg:80,93.5 ...)</td>
		  		</tr>
		  		<tr>
		  			<td>Current Location:</td>
		  			<td><input type="text" name="Location"></td>
		  			<td>(eg:Shanghai,Shenzhen...)</td>
		  		</tr>
		  		<tr>
		  			<td>Is Working at Customer site:</td>
		  			<td><input type="checkbox" name="chkCustomer"></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Is OnBench:</td>
		  			<td><input type="checkbox" name="chkOnBench"></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td>Is Regiestered:<a target="_blank" href="https://w3-connections.ibm.com/communities/service/html/communityview?communityUuid=eebba8fa-fc09-4465-8916-5e4542c44951#fullpageWidgetId=Waa361913e535_42f1_8ef3_c6a090f53917&file=40c93b05-787e-440a-a1e9-214a97208f21">register guide</a></td>
		  			<td><input type="checkbox" name="chkRegiestered"></td>
		  			<td></td>
		  		</tr>
	  			<tr>
	  				<td><input type="submit" class="btn btn-primary" value="Register" /></td>
		  			<td><button type="button" class="btn" onclick="F2()">Back</button></td>
		  			<td></td>
		  		</tr>
	  		</table>
	  	</div>
	  </div>
  </form>
</body>
</html>