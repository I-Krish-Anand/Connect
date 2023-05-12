<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connect</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="Temp.css">
</head>
<body>

	<div id="setuprofile">

		<label>Enter a punchy intro</label> <input type="text" id="bio"
			placeholder="Bio"> <label>What's your profession?</label> <input
			type="text" id="career" placeholder="Profession"> <label>Who
			knows you the best ?</label> <input type="text" id="whoKnowsYou"
			placeholder="describe the Person"> <label>Interests</label>
		<button type="button" class="interest-btn" data-value="music">Music</button>
		<button type="button" class="interest-btn" data-value="sports">Sports</button>
		<button type="button" class="interest-btn" data-value="reading">Reading</button>
		<button type="button" class="interest-btn" data-value="cooking">Cooking</button>
		<button type="button" class="interest-btn" data-value="travel">Travel</button>
		<button type="button" class="interest-btn" data-value="photography">Photography</button>
		<button type="button" class="interest-btn" data-value="art">Art</button>
		<button type="button" class="interest-btn" data-value="gaming">Gaming</button>
		<button type="button" class="interest-btn" data-value="coding">Coding</button>

		<button onclick="updateProfile()">Save</button>

	</div>

	<div id="QuestionsSetup">

		<h3 id="welcome">Welcome!</h3>

		<h3 id="hint">Here's a little secret, don't stick around a single
			subject, play around</h3>

		<p id="question_no">1/5</p>

		<label for="question">Question:</label><br> <input type="text"
			id="question" name="question" required><br>

		<div style="width: 20vw">
			<label for="option1">Option 1:</label><br> <input type="text"
				id="option1" name="option1" style="font-size: 14px;" required><br>

			<label for="option2">Option 2:</label><br> <input type="text"
				id="option2" name="option2" style="font-size: 14px;" required><br>

			<label for="option3">Option 3:</label><br> <input type="text"
				id="option3" name="option3" style="font-size: 14px;" required><br>

			<label for="correctAnswer">Correct Answer:</label><br> <select
				id="correctAnswer" name="correctAnswer" style="font-size: 14px;"
				required>
				<option value="1">Option 1</option>
				<option value="2">Option 2</option>
				<option value="3">Option 3</option>
			</select><br> <br>
		</div>

		<button onClick="setQuestions()">Next</button>

	</div>


	<div id="ConnectApp">

		<div id="HomePage">

			<div class="quiz-container">
				<button class="arrow button left-arrow" id="left"
					onclick="handleLeftArrowClick()">&#8592;</button>
				<h2 class="question" id="randQuestion"></h2>
				<div id="optionsToChoose">
					<input type="radio" name="myRadio" value="1"> <label
						id="label1">Option 1</label><br> <br> <input
						type="radio" name="myRadio" value="2"> <label id="label2">Option
						2</label><br> <br> <input type="radio" name="myRadio" value="3">
					<label id="label3">Option 3</label>
				</div>

				<button class="arrow button right-arrow" id="right"
					onclick="handleRightArrowClick()">&#8594;</button>
			</div>



		</div>



		<div class="match-alert">
			<span class="match-alert-close">&times;</span>
			<div class="match-alert-header">Is it destiny?</div>
			<div id="myMatch" class="match-alert-message">Hurray, You have
				a match with</div>
		</div>

		<div id="settings">

			<div id="sets">
				<div class="sam">
					<label for="match-with">Match with:</label> <select id="match-with"
						name="match-with">
						<option value="men">Men</option>
						<option value="women">Women</option>
						<option value="everyone">Everyone</option>
					</select>
				</div>
				<div class="sam">
					<label for="age">Age:</label>
					<div class="range-slider">
						<input type="range" id="age-min" name="age-min" min="18" max="120"
							value="18"> <input type="range" id="age-max"
							name="age-max" min="18" masx="120" value="120"> <span
							id="age-value">18 - 120</span>
					</div>
				</div>
				<br>
				<button class="sam" onclick="settings()" id="save">Save My
					Choices</button>
				<p id="currentStatus"></p>
			</div>
		</div>

		<div id="matchtable"
			style="border-right: 1px solid black; height: 80vh; width: 65vw">
			<table class="table container" style="width: 60vw;">
				<p id="friendStatus"></p>
				<tbody id="matches">
				</tbody>
			</table>
		</div>




		<span id="status"></span>


		<div class="footer" id="footer">

			<button class="button" onclick="friends()">Friends</button>
			<button class="button" onclick="fetchQuestions()">Home</button>
			<button class="button" onclick="showSettings()">Settings</button>
		</div>



	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script type="text/javascript" src="AppPage.js"></script>
</body>
</html>