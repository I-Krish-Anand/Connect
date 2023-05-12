package com.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.connect.features.FeaturesViewModel;
import com.connect.model.Question;

@WebServlet("/questions")
public class SetQuestions extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		FeaturesViewModel featuresViewModel = new FeaturesViewModel();
		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(request.getReader().readLine());
			JSONArray arr = (JSONArray) obj.get("questions");
			int size = 0;
			Question[] questions = new Question[5];

			for (Object object : arr) {
				JSONObject jsonObj = (JSONObject) object;
				String question = (String) jsonObj.get("question");
				String option1 = (String) jsonObj.get("option1");
				String option2 = (String) jsonObj.get("option2");
				String option3 = (String) jsonObj.get("option3");
				int answerKey = Integer.parseInt( (String) jsonObj.get("answerKey"));

				Question questionObject = new Question(question, option1, option2, option3, answerKey);
				questions[size++] = questionObject;
			}
			featuresViewModel.setQuestions(username, questions);
			try {
				response.getWriter().print(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}
}
