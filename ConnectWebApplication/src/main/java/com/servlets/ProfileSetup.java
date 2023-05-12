package com.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
import com.connect.model.User;

@WebServlet("/ProfileSetup")
public class ProfileSetup extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		FeaturesViewModel featuresViewModel = new FeaturesViewModel();

		String username = (String) session.getAttribute("username");
		

		// Parse the JSON string into a JSON object to get the interests

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String requestBody = sb.toString();
			
			// Parse the request body as a JSON object
			JSONObject json = null;
			try {
				JSONParser parser = new JSONParser();
				json = (JSONObject) parser.parse(requestBody);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			// Retrieve the values from the JSON object
			String bio = (String) json.get("bio");
			String career = (String) json.get("career");
			String whoKnowsYou = (String) json.get("whoKnowsYou");
			
			List<String> selectedInterests = new ArrayList<>();
			JSONArray interests = (JSONArray) json.get("selectedInterests");
			for (Object interest : interests) {
				selectedInterests.add((String) interest);
			}
           

			User user = new User();
			user.setUsername(username);
			user.setBio(bio);
			user.setCareer(career);
			user.setWhoKnowsYou(whoKnowsYou);
			user.setInterests(selectedInterests);
			System.out.println(selectedInterests);
			featuresViewModel.profileUpdate(user);

			try {
				response.getWriter().print(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch(Exception e) {}

	}
}
