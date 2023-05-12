package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.connect.features.FeaturesViewModel;
import com.connect.model.Question;

@WebServlet("/fetchquestion")
public class FetchQuestions extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		FeaturesViewModel featuresViewModel = new FeaturesViewModel();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		response.setContentType("application/json");

		try {
			Question ques = featuresViewModel.swipe(username);
			if (ques == null) {
				JSONObject obj = new JSONObject();
				obj.put("message", "No questions found");
				response.getWriter().print(obj.toString());
			} else {
				JSONArray arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("questioner", ques.getUsername());
				obj.put("opt1", ques.getOption1());
				obj.put("opt2", ques.getOption2());
				obj.put("opt3", ques.getOption3());
				obj.put("que", ques.getQuestion());
				obj.put("ans", ques.getAnswerKey());
				obj.put("ques_id", ques.getQuestion_id());
				arr.add(obj);
				JSONObject resultObject = new JSONObject();
				resultObject.put("questions", arr);
				response.getWriter().print(resultObject.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
