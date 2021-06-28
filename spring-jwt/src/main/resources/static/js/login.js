/**
 * 
 */

var LOGIN_API = "http://localhost:8080/authenticate";

var EMP_DETAILS_API = "http://localhost:8080/empDetails"

window.onload = loadCookies;

var xhr = new XMLHttpRequest();

function loadCookies() {

	//sometimes the order change 	
	
//    var cookies = document.cookie.split(';');
//    
//	var email = cookies[0].split("email=");
//    var password = cookies[1].split("password=");
//
//    document.getElementById("email").value = email[1];
//    document.getElementById("password").value = window.atob(password[1]);
	
	var cookies = document.cookie
							.split(';')
							.map(cookie => cookie.split('='))
							.reduce((accumulator, [key, value]) => ({...accumulator, [key.trim()]: decodeURIComponent(value)}), {});
	
	if(cookies.email  == undefined || cookies.password == undefined) {
	    return false;
    }

	document.getElementById("email").value = cookies.email;
    document.getElementById("password").value = window.atob(cookies.password);    
	
	document.getElementById('rememberCheck').checked = true;
	
}

var loginBut = document.getElementById("loginBut");

loginBut.addEventListener('click', validateUser);

//call authentication mapping in controller and validate user credentials return token if valid
function validateUser() {
	
	var emailId = document.getElementById("email").value;
	var password = window.btoa(document.getElementById("password").value);
	
	event.preventDefault();
	
	xhr.open("POST", LOGIN_API, true);
	
	xhr.setRequestHeader("Content-Type", "application/json");
	
	var employee = {"emailId":emailId, "password":password};
	
	xhr.send(JSON.stringify(employee));
	
	xhr.onreadystatechange = function() {
				
		if(xhr.readyState == 4 && xhr.status == 560) {		
			$("#actionblock").modal("show");
		}
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			//for return token
			var token = xhr.responseText;
			console.log(token);
			getEmployeeDetails(token);
			
			//for return employee details with token in response header			
//			console.log(xhr.getResponseHeader("Authorization"));
			
//			var emp = JSON.parse(xhr.responseText);
//			console.log(emp);
//			console.log("emp.id")
			//save cookie only if user is valid
			saveCookie(emailId, password);
			
//			callBookACab(token);
						
		}

	}
		
}

//call empDetails in controller
function getEmployeeDetails(token) {
	
	xhr = new XMLHttpRequest();

	xhr.open("GET", EMP_DETAILS_API, true);
	//setting token at request header
	xhr.setRequestHeader("AUTHORIZATION", token);
	xhr.send(null);
	
	xhr.onreadystatechange = function() {
				
		if(xhr.readyState == 4 && xhr.status == 200){
			var employee = JSON.parse(xhr.responseText);
			console.log(employee);
			console.log(employee.id);
		}
		
	}
	
}

function saveCookie(emailId, password) {
    
	if(document.getElementById('rememberCheck').checked == true) {
        
        var d = new Date();
        d.setTime(d.getTime() + 24*60*60*1000);
        d.toUTCString();

        document.cookie = "email="+emailId +";"+ "expires="+d + ";path=/";
        document.cookie = "password="+password +";"+ "expires="+d + ";path=/";

    }

	else {
		document.cookie = "email=" +";"+ "expires="+ (new Date() - 24*60)  + ";path=/";
        document.cookie = "password="+";"+ "expires="+(new Date() - 24*60) + ";path=/";
	}
	
}

//function callBookACab(token) {
//	
//	xhr = new XMLHttpRequest;
//		
//	xhr.open("GET", "http://localhost:8080/hello", true);
//		
//	xhr.setRequestHeader("AUTHORIZATION", token);	
//		
//	xhr.send(null);
//		
//	xhr.onreadystatechange = function() {
//				
//		if(xhr.readyState == 4 && xhr.status == 200){
//			window.location.href = "cab-app-completedtrip.html";
//		}
//		
//	}
//
//}

