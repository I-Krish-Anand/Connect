let xhs = new XMLHttpRequest();
let HomePage = document.getElementById("HomePage");
let settingsButton = document.getElementById("settings");
let matchesButton = document.getElementById("matches");
let footer = document.getElementById("footer");
let setuprofile = document.getElementById("setuprofile");

let QuestionsSetup = document.getElementById("QuestionsSetup");
let questions = [];
let welcome = document.getElementById("welcome");
let hint = document.getElementById("hint");
let answerKey = 'initial value';
let questioner = 'initial value';
let question_id = 'initial value';


const buttons = document.querySelectorAll('.interest-btn');
let count = 0;
const selectedInterests = [];

buttons.forEach((button) => {
	button.addEventListener('click', function(event) {
		if (button.classList.contains('selected')) {
			button.classList.remove('selected');
			count--;
			const index = selectedInterests.indexOf(button.dataset.value);
			if (index > -1) {
				selectedInterests.splice(index, 1);
			}
		} else if (count < 5) {
			button.classList.add('selected');
			count++;
			selectedInterests.push(button.dataset.value);
		} else {
			alert("You can only select up to 5 interests.");
		}
	});
});

const ageMinInput = document.getElementById('age-min');
const ageMaxInput = document.getElementById('age-max');
const ageValueSpan = document.getElementById('age-value');
ageMinInput.addEventListener('input', updateAgeValue);
ageMaxInput.addEventListener('input', updateAgeValue);

function updateAgeValue() {

	let ageMin = parseInt(ageMinInput.value);
	let ageMax = parseInt(ageMaxInput.value);
	console.log(ageMin + " " + ageMax);

	ageValueSpan.textContent = `${ageMin} - ${ageMax}`;
}

window.onload = function() {

	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {
			let res = JSON.parse(xhs.responseText);
			console.log(res);
			if (!res) {
				HomePage.style.display = "none";
				setuprofile.style.display = "block";
				QuestionsSetup.style.display = "none";
			}
			else
				checkRegisterationStatus();

		}
	}

	xhs.open("POST", "/ConnectWebApplication/ProfileUpdationStatus");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send();

}

function checkRegisterationStatus() {

	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {
			let res = JSON.parse(xhs.responseText);
			console.log(res);
			if (!res) {
				HomePage.style.display = "none";
				setuprofile.style.display = "none";
				QuestionsSetup.style.display = "block";
			}
			else {
				HomePage.style.display = "block";
				QuestionsSetup.style.display = "none";
				setuprofile.style.display = "none";
				footer.style = "display:flex";
				fetchQuestions();
			}

		}
	}
	xhs.open("POST", "/ConnectWebApplication/registerationstatus");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send();



}

function updateProfile() {
	const bioVar = document.getElementById("bio").value;
	const careerVar = document.getElementById("career").value;
	const whoKnowsYouVar = document.getElementById("whoKnowsYou").value;

	let bio = bioVar;
	let career = careerVar;
	let whoKnowsYou = whoKnowsYouVar;

	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {
			let res = JSON.parse(xhs.responseText);
			HomePage.style.display = "none";
			QuestionsSetup.style.display = "block";
			footer.style = "none";
			setuprofile.style.display = "none";
		}
	}

	const data = {
		bio: bio,
		career: career,
		whoKnowsYou: whoKnowsYou,
		selectedInterests: selectedInterests
	};

	xhs.open("POST", "/ConnectWebApplication/ProfileSetup");
	xhs.setRequestHeader("Content-Type", "application/json");
	xhs.send(JSON.stringify(data));
}


function setQuestions() {
	if (questions.length < 5) {


		let question = document.getElementById("question");
		let option1 = document.getElementById("option1");
		let option2 = document.getElementById("option2");
		let option3 = document.getElementById("option3");
		let answerKey = document.getElementById("correctAnswer");


		questions.push({
			"question": question.value,
			"option1": option1.value,
			"option2": option2.value,
			"option3": option3.value,
			"answerKey": answerKey.value
		});

		document.getElementById("question_no").innerText = (questions.length + 1) + "/5";
		if (questions.length > 1) {
			welcome.style.display = "none";
			hint.style.display = "block";
		}

		question.value = "";
		option1.value = "";
		option2.value = "";
		option3.value = "";
		answerKey.value = "";
	}

	if (questions.length >= 5) {


		xhs.onreadystatechange = function() {
			if (xhs.readyState == 4) {
				let res = JSON.parse(xhs.responseText);
				console.log(res);
				welcome.style.display = "none";
				hint.style.display = "none";
				HomePage.style.display = "block";
				QuestionsSetup.style.display = "none";
				footer.style.display = "flex";
				fetchQuestions();

			}
		}

		xhs.open("POST", "/ConnectWebApplication/questions");
		xhs.setRequestHeader("Content-Type", "application/json");
		xhs.send(JSON.stringify({ "questions": questions }));
	}

}


function fetchQuestions() {


	let status = document.getElementById("currentStatus");
	status.innerText = "";
	HomePage.style.display = "block";
	settingsButton.style.display = "none";
	matchesButton.style.display = "none";
	document.getElementById("matchtable").style.display = "none";
	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {
			let res = JSON.parse(xhs.responseText);
			if (res.message === "No questions found") {
				document.getElementById("randQuestion").innerText = "There's no one to swipe, try again later";
				document.getElementById("optionsToChoose").style.display = "none";
			} else {

				var radioButtons = document.getElementsByName('myRadio');
				for (var i = 0; i < radioButtons.length; i++) {
					radioButtons[i].checked = false;
				}
				document.getElementById("optionsToChoose").style.display = "block";
				questioner = res.questions[0].questioner;
				let question = res.questions[0].que;
				let opt1 = res.questions[0].opt1;
				let opt2 = res.questions[0].opt2;
				let opt3 = res.questions[0].opt3;
				answerKey = res.questions[0].ans;
				question_id = res.questions[0].ques_id;
				document.getElementById("randQuestion").innerText = question;
				const labels = [
					{ id: 'label1', text: opt1 },
					{ id: 'label2', text: opt2 },
					{ id: 'label3', text: opt3 },
				];

				const radioInputs = document.querySelectorAll('input[name="myRadio"]');
				radioInputs.forEach((input, index) => {
					const label = document.getElementById(labels[index].id);
					label.textContent = labels[index].text;
				});

			}
		}
	}



	xhs.open("POST", "/ConnectWebApplication/fetchquestion");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send();


}


function handleRightArrowClick() {

	let chosenAnswer = document.querySelector('input[name="myRadio"]:checked').value;
	xhs.onreadystatechange = function() {
		console.log("onreadystatechange called with readyState", xhs.readyState);
		if (xhs.readyState == 4) {
			let res = parseInt(xhs.responseText);
			if (res == 3) {
				console.log("Match should appear");
				showMatch();

			}
			fetchQuestions();

		}
	}

	xhs.open("POST", "/ConnectWebApplication/reply");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send("myAnswer=" + chosenAnswer + "&answer=" + answerKey + "&questioner=" + questioner + "&question_id=" + question_id);

}

function showMatch() {

	console.log("Function called");
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		console.log("onreadystatechange called with readyState", xhr.readyState);

		if (xhr.readyState == 4) {
			console.log("Entering ready state");
			let res = JSON.parse(xhr.responseText);
			let name = res.name;
			let username = res.username;
			let age = res.age;
			let gender = res.gender;
			let bio = res.bio;
			let career = res.career;
			let interests = res.interests ? res.interests.join(', ') : '';

			
			let matchAlert = document.querySelector('.match-alert');
			let message = `Hurray, You have a match with ${name} (${username})!\n\nAge: ${age}\nGender: ${gender}\nBio: ${bio}\nCareer: ${career}\nInterests: ${interests}`;
			matchAlert.querySelector('.match-alert-message').textContent = message;
			matchAlert.style.display = 'block';

			setTimeout(function() {
				document.querySelector('.match-alert').style.display = 'none';
			}, 10000); // Hide the alert box after 5 seconds

		}
	}


	xhr.open("POST", "/ConnectWebApplication/ShowProfile");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("questioner=" + questioner);

}

function handleLeftArrowClick() {


	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {
			fetchQuestions();

		}
	}

	xhs.open("POST", "/ConnectWebApplication/askedQuestions");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send("question_id=" + question_id);
}


function friends() {

	let status = document.getElementById("currentStatus");
	status.innerText = "";
	HomePage.style.display = "none";
	settingsButton.style.display = "none";
	matchesButton.style.display = "block";
	document.getElementById("matchtable").style.display = "block";
	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {


			let res = JSON.parse(xhs.responseText)

			if (res.message === "No questions found") {
				document.getElementById("friendStatus").innerHTML = "Start swiping to connect";
			} else {

				const matchesList = document.getElementById("matches");
				console.log(res);
				console.log(res.users[0].name);
				matchesList.innerHTML = "";
				for (let i = 0; i < res.users.length; i++) {
					let data = `<tr class="friend-row" data-id="${res.users[i].id}">
                        <td>
                            <svg style="width: 50px;height: 50px;" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </td>
                        <td class="d-flex flex-column" style="margin-left:100px;margin-right:100px;">
                            <h6>${res.users[i].name}</h6>
                            <p>${res.users[i].username}</p>
                        </td>
                        <td>time</td>
                    </tr>`;
					matchesList.innerHTML += data;
				}
				// Add event listener to each friend row to display user details when clicked
				const friendRows = document.getElementsByClassName("friend-row");
				for (let i = 0; i < friendRows.length; i++) {
					friendRows[i].addEventListener("click", displayUserDetails);
				}

			}
		}
	}



	xhs.open("POST", "/ConnectWebApplication/ShowFriendsServlet");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send();
}





function settings() {

	let choice = document.getElementById("match-with");
	let ageMin = parseInt(ageMinInput.value);
	let ageMax = parseInt(ageMaxInput.value);
	console.log("Settings:" + ageMin + " " + ageMax);

	xhs.onreadystatechange = function() {
		if (xhs.readyState == 4) {
			let status = document.getElementById("currentStatus");
			status.innerHTML = "Preferences updated successfully";
		}
	};

	xhs.open("POST", "/ConnectWebApplication/SettingsServlet");
	xhs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhs.send("choice=" + choice.value + "&ageMin=" + ageMin + "&ageMax=" + ageMax);





}


function showSettings() {
	HomePage.style.display = "none";
	settingsButton.style.display = "flex";
	matchesButton.style.display = "none";
	document.getElementById("matchtable").style.display = "none";
}