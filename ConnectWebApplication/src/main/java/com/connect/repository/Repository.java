package com.connect.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.connect.model.Question;
import com.connect.model.User;

public class Repository {

	private static Repository repository;

	private Repository() {
	}

	private static Connection connection;

	private static ResultSet resultSet;

	private static Statement statement;

	public static Repository getInstance() {
		if (repository == null) {

			repository = new Repository();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connect", "root", "root");
			}

			catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		return repository;
	}

	public boolean verifyCredentials(String username, String password) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement("SELECT * FROM userdetails WHERE username=? AND password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createAccount(String name, String phoneNumber, String username, String password, int age,
			String gender) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO userdetails (name,phonenumber,username,password,age,gender)  values(?,?,?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phoneNumber);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, password);
			preparedStatement.setInt(5, age);
			preparedStatement.setString(6, gender);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean validateUsername(String username) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * from userdetails where username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				System.out.println("Username already exists:");
			return !resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean registerationStatus(String username) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * from userdetails where username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getBoolean("registerationcompleted");

		} catch (SQLException e) {
		}
		return false;
	}

	public boolean profileUpdationStatus(String username) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * from userdetails where username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getBoolean("profileUpdationCompleted");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void profileUpdate(User user) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO profileDetails values(?,?,?,?)");
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getBio());
			preparedStatement.setString(3, user.getCareer());
			preparedStatement.setString(4, user.getWhoKnowsYou());
			preparedStatement.executeUpdate();

			for (String interest : user.getInterests()) {
				preparedStatement = connection.prepareStatement("INSERT INTO interests values(?,?)");
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, interest);
				preparedStatement.executeUpdate();
			}

			preparedStatement = connection
					.prepareStatement("UPDATE  userdetails SET profileUpdationCompleted=? where username=?");
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setQuestions(String username, Question[] questions) {

		PreparedStatement preparedStatement;
		try {

			for (Question question : questions) {

				preparedStatement = connection.prepareStatement(
						"INSERT INTO questions (username,question,option1,option2,option3,answerkey) values(?,?,?,?,?,?)");
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, question.getQuestion());
				preparedStatement.setString(3, question.getOption1());
				preparedStatement.setString(4, question.getOption2());
				preparedStatement.setString(5, question.getOption3());
				preparedStatement.setInt(6, question.getAnswerKey());
				preparedStatement.executeUpdate();

				preparedStatement = connection
						.prepareStatement("UPDATE  userdetails SET registerationcompleted=? where username=?");
				preparedStatement.setBoolean(1, true);
				preparedStatement.setString(2, username);
				preparedStatement.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Question swipe(String username) {

//		PreparedStatement preparedStatement;
//		try {
//            
//			preparedStatement = connection.prepareStatement("SELECT * FROM userdetails where username=?");
//			preparedStatement.setString(1, username);
//			resultSet = preparedStatement.executeQuery();
//
//			if (resultSet.next()) {
//
//				System.out.println(resultSet.getString("minAgePreffered")+" "+resultSet.getString("maxAgePreffered")+" "+resultSet.getString("genderPreffered"));
//				if (resultSet.getString("genderPreffered").toLowerCase().equals("everyone")) {
//
//					PreparedStatement preparedStatement2 = connection.prepareStatement(
//							"SELECT * from questions WHERE  NOT username=? AND username IN ( SELECT username from  userdetails WHERE age >=? AND age <= ? )AND question_id not in(select question_id from asked_questions where username=?) order by RAND()LIMIT 1");
//					preparedStatement2.setInt(2, resultSet.getInt("minAgePreffered"));
//					preparedStatement2.setString(1, username);
//					preparedStatement2.setInt(3, resultSet.getInt("maxAgePreffered"));
//					preparedStatement2.setString(4, resultSet.getString("username"));
//					resultSet = preparedStatement2.executeQuery();
//
//				} else {
//
//					preparedStatement = connection.prepareStatement(
//							"SELECT * from questions WHERE  NOT username=? AND username IN ( SELECT username from  userdetails WHERE age >=? AND age <= ? AND gender =?)AND question_id not in(select question_id from asked_questions where username=?) order by RAND()LIMIT 1");
//					preparedStatement.setString(1, username);
//					preparedStatement.setInt(2, resultSet.getInt("minAgePreffered"));
//					preparedStatement.setInt(3, resultSet.getInt("maxAgePreffered"));
//					preparedStatement.setString(4, resultSet.getString("genderPreffered"));
//					preparedStatement.setString(5, username);
//					resultSet = preparedStatement.executeQuery();
//
//				}
//
//				if (resultSet.next()) {
//					Question question = new Question(resultSet.getString("question"), resultSet.getString("option1"),
//							resultSet.getString("option2"), resultSet.getString("option3"),
//							resultSet.getInt("answerKey"));
//					question.setQuestion_id(resultSet.getInt("question_id"));
//					question.setUsername(resultSet.getString("username"));
//					return question;
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;

		PreparedStatement preparedStatement;
		ResultSet resultSet1, resultSet2;
		try {

			preparedStatement = connection.prepareStatement("SELECT * FROM userdetails where username=?");
			preparedStatement.setString(1, username);
			resultSet1 = preparedStatement.executeQuery();

			if (resultSet1.next()) {

				System.out.println(resultSet1.getString("minAgePreffered") + " "
						+ resultSet1.getString("maxAgePreffered") + " " + resultSet1.getString("genderPreffered"));
				if (resultSet1.getString("genderPreffered").toLowerCase().equals("everyone")) {

					PreparedStatement preparedStatement2 = connection.prepareStatement(
							"SELECT * from questions WHERE  NOT username=? AND username IN ( SELECT username from  userdetails WHERE age >=? AND age <= ? )AND question_id not in(select question_id from asked_questions where username=?) order by RAND()LIMIT 1");
					preparedStatement2.setInt(2, resultSet1.getInt("minAgePreffered"));
					preparedStatement2.setString(1, username);
					preparedStatement2.setInt(3, resultSet1.getInt("maxAgePreffered"));
					preparedStatement2.setString(4, resultSet1.getString("username"));
					resultSet2 = preparedStatement2.executeQuery();

				} else {

					preparedStatement = connection.prepareStatement(
							"SELECT * from questions WHERE  NOT username=? AND username IN ( SELECT username from  userdetails WHERE age >=? AND age <= ? AND gender =?)AND question_id not in(select question_id from asked_questions where username=?) order by RAND()LIMIT 1");
					preparedStatement.setString(1, username);
					preparedStatement.setInt(2, resultSet1.getInt("minAgePreffered"));
					preparedStatement.setInt(3, resultSet1.getInt("maxAgePreffered"));
					preparedStatement.setString(4, resultSet1.getString("genderPreffered"));
					preparedStatement.setString(5, username);
					resultSet2 = preparedStatement.executeQuery();

				}

				if (resultSet2.next()) {
					Question question = new Question(resultSet2.getString("question"), resultSet2.getString("option1"),
							resultSet2.getString("option2"), resultSet2.getString("option3"),
							resultSet2.getInt("answerKey"));
					question.setQuestion_id(resultSet2.getInt("question_id"));
					question.setUsername(resultSet2.getString("username"));
					return question;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int reply(String questioner, String username, int chosenAnswer, int answerkey, int question_id) {

		PreparedStatement preparedStatement;
		try {

			updateAskedQuestions(username, question_id);
			int sum1 = 0, sum2 = 0;
			if (chosenAnswer == answerkey) {
				preparedStatement = connection.prepareStatement(
						"INSERT INTO match_count values(?,?,1) ON DUPLICATE KEY UPDATE score=score+1");
				preparedStatement.setString(1, questioner);
				preparedStatement.setString(2, username);
				preparedStatement.executeUpdate();

				preparedStatement = connection
						.prepareStatement("SELECT *  from match_count where questioner=? AND replier=?");
				preparedStatement.setString(1, questioner);
				preparedStatement.setString(2, username);
				resultSet = preparedStatement.executeQuery();

				if (resultSet.next())
					sum1 = resultSet.getInt("score");

				preparedStatement = connection
						.prepareStatement("SELECT *  from match_count where questioner=? AND replier=?");
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, questioner);
				resultSet = preparedStatement.executeQuery();

				if (resultSet.next())
					sum2 = resultSet.getInt("score");

				if (sum1 + sum2 == 3) {

					preparedStatement = connection.prepareStatement("INSERT INTO  friends values(?,?,now())");
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, questioner);
					preparedStatement.executeUpdate();
				}

				return sum1 + sum2;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public void updateAskedQuestions(String username, int question_id) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO asked_questions values(?,?)");
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, question_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public User showProfile(String username) {

		PreparedStatement preparedStatement;
		try {
			User user = new User();
			preparedStatement = connection.prepareStatement("SELECT * from userdetails where username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				user.setName(resultSet.getString("name"));
				user.setGender(resultSet.getString("gender"));
				user.setAge(resultSet.getInt("age"));
				user.setUsername(username);

			}

			preparedStatement = connection.prepareStatement("SELECT * from profileDetails where username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				user.setBio(resultSet.getString("bio"));
				user.setCareer(resultSet.getString("profession"));
				user.setWhoKnowsYou(resultSet.getString("whoKnowsYou"));

			}

			List<String> list = new ArrayList<>();
			preparedStatement = connection.prepareStatement("SELECT * from interests where username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(resultSet.getString("interest"));

			user.setInterests(list);

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List getFriends(String username) {

		System.out.println("Hello moto");

		List<User> userlist = new ArrayList<>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT * from userdetails where username in(SELECT friend from friends where username=? ) ");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setName(resultSet.getString("name"));
				user.setUsername(resultSet.getString("username"));
				user.setAge(resultSet.getInt("age"));
				user.setGender(resultSet.getString("gender"));
				userlist.add(user);
				System.out.println("Hello moto");
				System.out.println(resultSet.getString("name"));

			}

			preparedStatement = connection.prepareStatement(
					"SELECT * from userdetails where username in(SELECT username from friends where friend=? ) ");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setName(resultSet.getString("name"));
				user.setUsername(resultSet.getString("username"));
				user.setAge(resultSet.getInt("age"));
				user.setGender(resultSet.getString("gender"));
				System.out.println("Hello moto");
				System.out.println(resultSet.getString("name"));
				userlist.add(user);

			}
			System.out.print(userlist);
			return userlist;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void settingsUpdate(User user) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE userdetails set minAgePreffered=?, maxAgePreffered=?, GenderPreffered=? where username=?");
			preparedStatement.setInt(1, user.getMinAgePreffered());
			preparedStatement.setInt(2, user.getMaxagePreffered());
			preparedStatement.setString(3, user.getGenderPrefered());
			preparedStatement.setString(4, user.getUsername());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
