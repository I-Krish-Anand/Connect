package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet ("/ShowProfile")
public class ShowProfile extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		String username=request.getParameter("questioner");
		System.out.print(username);
		FeaturesViewModel featuresViewModel = new FeaturesViewModel();
		response.setContentType("application/json");
		try {
			User user = featuresViewModel.showProfile(username);
			System.out.println(user.getAge()+" "+user.getName());
				JSONObject obj = new JSONObject();
				List<String>list=new ArrayList<>();
				list.add("cricket");
				user.setInterests(list);
				obj.put("username", user.getUsername());
				obj.put("name", user.getName());
				obj.put("age", user.getAge());
				obj.put("bio", user.getBio());
				obj.put("career", user.getCareer());
				obj.put("gender", user.getGender());
				obj.put("whoKnowsYou", user.getWhoKnowsYou());
				obj.put("interests", user.getInterests());	
				String responseString = obj.toString();
				System.out.println("Sending response: " + responseString);
				response.getWriter().print(responseString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}
