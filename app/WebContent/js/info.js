function checkform() {
  		var numObj = document.getElementById("Utilization");
		if (isNaN(numObj.value)) {
			numObj.value = "";
			numObj.focus();
			document.getElementById("utInfo").style.display = "block";
			return false;
		}
		document.getElementById("utInfo").style.display = "none";
		
		var notesID = document.getElementById("notesID");
		var notesIDInfo = document.getElementById("notesIDInfo");
		var testReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		if (!testReg.test(notesID.value)) {
			notesIDInfo.innerHTML = "please enter IBM e-mail address!";
			notesID.value = "";
			notesID.focus();
			return false;
		}
		notesIDInfo.innerHTML = "";
		return true;
  	}
  	
  	function chkNotesID() {
		var notesID = document.getElementById("notesID");
		var notesIDInfo = document.getElementById("notesIDInfo");
		var testReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		if (!testReg.test(notesID.value)) {
			notesIDInfo.innerHTML = "please enter IBM e-mail address!";
			notesID.value = "";
			notesID.focus();
			return false;
		}
		notesIDInfo.innerHTML = "";
		return true;
  	}
  	
	function chkNum(numObj) {
		if (isNaN(numObj.value)) {
			numObj.value = "";
			numObj.focus();
			document.getElementById("utInfo").style.display = "block";
			return false;
		}
		document.getElementById("utInfo").style.display = "none";
		return true;
	}