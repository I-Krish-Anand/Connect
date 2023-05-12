package com.connect.model;

public class Question {
	
	String username;
	
	String question;
	
	String option1;
	
	String option2;
	
	String option3;
	
	int answerKey;
	
	int question_id;
	
	int userChoice;
	
	
	public Question(String question, String option1, String option2, String option3, int answerKey) {
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.answerKey = answerKey;
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getUserChoice() {
		return userChoice;
	}





	public void setUserChoice(int userChoice) {
		this.userChoice = userChoice;
	}





	public int getQuestion_id() {
		return question_id;
	}



	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}



	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public int getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(int answerKey) {
		this.answerKey = answerKey;
	}
	
	
	
	

}
