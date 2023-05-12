package com.connect.features;

import java.util.List;

import com.connect.model.Question;
import com.connect.model.User;
import com.connect.repository.Repository;

public class FeaturesViewModel {

	public boolean registerationStatus(String username) {
		return Repository.getInstance().registerationStatus(username);
	}

	public void setQuestions(String username, Question[] questions) {
		Repository.getInstance().setQuestions(username, questions);
	}

	public Question swipe(String username) {
		return Repository.getInstance().swipe(username);
	}

	public void swipeLeft() {

	}

	public void swipeRight() {

	}

	public int reply(String questioner, String username, int chosenAnswer, int anwerKey, int question_id) {
		return Repository.getInstance().reply(questioner, username, chosenAnswer, anwerKey, question_id);
	}

	public void updateAskedQuestions(String username, int question_id) {
		Repository.getInstance().updateAskedQuestions(username, question_id);
	}

	public List<User> getFriends(String username) {
		return Repository.getInstance().getFriends(username);

	}

	public void settingsUpdate(User user) {
		Repository.getInstance().settingsUpdate(user);		
	}
	
	public boolean profileUpdationStatus(String username) {
		return Repository.getInstance().profileUpdationStatus(username);
	}
	
	public void profileUpdate(User user) {
		Repository.getInstance().profileUpdate(user);
	}
	
	public User showProfile(String username) {
		return Repository.getInstance().showProfile(username);
	}

}
