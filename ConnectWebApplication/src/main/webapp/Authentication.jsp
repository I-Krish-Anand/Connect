<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="Authentication.css">
</head>
<body>
	<div id="login">

		<input type="text" id="username" placeholder="Username"> <input
			type="password" id="password" placeholder="Password">
		<button onclick="handleLogin()">Login</button>
		<p onclick="handleSwitchAuth()">Create new account</p>

	</div>

	<div id="signup">

		<input type="text" id="name" placeholder="First Name" required>
		<span class="messageStatus" id="nameStatus"></span> <input type="text"
			id="phonenumber" placeholder="+91 " required> <span
			class="messageStatus" id="phoneNoStatus">Exclude country code</span>
		<input type="text" id="user_name" placeholder="Set username" required>
		<span class="messageStatus" id="usernameStatus">Username should
			be unique and should at least consist of 5 characters, you are
			allowed to use only alphabets and numbers</span> <input type="password"
			id="pass" placeholder="Set password" required> <span
			class="messageStatus" id="passwordStatus">Password should be
			at least 8 characters long, with a special character, a upper case
			character and a number</span> <input type="text" id="age" placeholder="Age"
			required> <span class="messageStatus" id="ageStatus"></span>

		<div>
			<input type="radio" name="gender" value="men" required> Male
			<input type="radio" name="gender" value="women"> Female
			<div class="messageStatus" id="genderStatus"></div>
		</div>

		<button onClick="handleSignUp()">Sign-up</button>
		<p onclick="handleSwitchAuth()">Existing user? Login</p>


	</div>


	<div style="text-align: center;" id="status"></div>

	<script type="text/javascript" src="Connect.js"></script>
</body>
</html>