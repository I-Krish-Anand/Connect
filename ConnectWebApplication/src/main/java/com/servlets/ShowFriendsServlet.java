package com.servlets;

import java.io.Console;
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
import com.connect.model.User;

@WebServlet("/ShowFriendsServlet")
public class ShowFriendsServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		FeaturesViewModel featuresViewModel = new FeaturesViewModel();
		response.setContentType("application/json");
		List<User> users = featuresViewModel.getFriends(username);

		try {
			if (users.isEmpty()) {
				JSONObject obj = new JSONObject();
				obj.put("message", "No users");
				response.getWriter().print(obj.toString());
			} else {
				JSONArray arr = new JSONArray();

				for (User user : users) {
					JSONObject obj = new JSONObject();
					obj.put("username", user.getUsername());
					obj.put("name", user.getName());
					obj.put("age", user.getAge());
					obj.put("gender", user.getGender());
					arr.add(obj);
				}
				JSONObject resultObject = new JSONObject();
				resultObject.put("users", arr);
				response.getWriter().print(resultObject.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
