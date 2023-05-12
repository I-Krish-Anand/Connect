let xhs = new XMLHttpRequest();
let login = document.getElementById("login");
let signup = document.getElementById("signup");
let isLogin = true;
let isRegisterationCompleted = false;
let isProfileSetupCompleted = false;

function home() {
	fetchQuestions();
}

function handleLogin() {

	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {
			let res = JSON.parse(xhs.responseText);

			if (res)
				location.replace("/ConnectWebApplication/AppPage.jsp");
			else
				document.getElementById("status").innerText = "Invalid Credentials"

		}
	}
	xhs.open("POST", "/ConnectWebApplication/login");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send("username=" + username + "&password=" + password);


}
function handleSwitchAuth() {
	
	
    document.getElementById("status").style.display="none";
	if (isLogin) {
		login.style.display = "none";
		signup.style.display = "block";
	} else {
		login.style.display = "block";
		signup.style.display = "none";
	}

	isLogin = !isLogin;

}


function handleSignUp() {

	document.getElementById("ageStatus").innerHTML = "";
	document.getElementById("phoneNoStatus").innerHTML = "Exclude Country code";
	document.getElementById("usernameStatus").innerHTML = "Username should be unique and should at least consist of 5 characters and Shouldn't exceed 20, you are allowed to use only alphabets and numbers";
	document.getElementById("passwordStatus").innerHTML = "Password should be at least 8 characters long, with a special character, a upper case character and a number";
	document.getElementById("genderStatus").innerHTML = "";

	document.getElementById("genderStatus").style.color = "grey";
	document.getElementById("phoneNoStatus").style.color = "grey";
	document.getElementById("passwordStatus").style.color = "grey";
	document.getElementById("usernameStatus").style.color = "grey";



	let name = document.getElementById("name").value;
	let phonenumber = document.getElementById("phonenumber").value;
	let username = document.getElementById("user_name").value;
	let password = document.getElementById("pass").value;
	let age = document.getElementById("age").value;
	let intAge = parseInt(age);
	let isInput = false;
	let isTrue = true;
	const radioButtons = document.querySelectorAll('input[type="radio"]');


	for (let i = 0; i < radioButtons.length; i++) {
		if (radioButtons[i].checked) {
			isInput = true;
		}
	}


	if (!/^\d{10}$/.test(phonenumber)) {
		document.getElementById("phoneNoStatus").innerHTML = "Enter a valid phone number";
		document.getElementById("phoneNoStatus").style.color = "red";
		isTrue = false;
	}

	if (!/^[a-z0-9]{5,20}$/.test(username)) {
		document.getElementById("usernameStatus").innerHTML = "Username should contain only lowercase characters and numbers";
		document.getElementById("usernameStatus").style.color = "red";
		isTrue = false;
	}

	if (!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,20}$/.test(password)) {
		document.getElementById("passwordStatus").innerHTML = "Password criteria not met";
		document.getElementById("passwordStatus").style.color = "red";
		isTrue = false;
	}

	if (age < 18 || age > 120) {
		document.getElementById("ageStatus").innerHTML = "You aren't allowed to use Connect considering your age";
		document.getElementById("ageStatus").style.color = "red";
		isTrue = false;
	}

	if (isInput == false) {
		document.getElementById("genderStatus").innerHTML = "Please check one of the radio boxes";
		document.getElementById("genderStatus").style.color = "red";
		isTrue = false;
	}


	if (!isTrue)
		return;

	let gender = document.querySelector('input[name="gender"]:checked').value;
	xhs.onreadystatechange = function() {

		if (xhs.readyState == 4) {

			let res = JSON.parse(xhs.responseText);
			console.log(res);
			if (res) {
				location.replace("/ConnectWebApplication/AppPage.jsp");
			}
			else
				document.getElementById("status").innerText = "Sign Up failed"
		}
	}
	
	xhs.open("POST", "/ConnectWebApplication/register");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send("name=" + name + "&phonenumber=" + phonenumber + "&username=" + username + "&password=" + password + "&age=" + age + "&gender=" + gender);

}

