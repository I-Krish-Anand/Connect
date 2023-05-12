package com.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.features.FeaturesViewModel;
import com.connect.model.User;


@WebServlet ("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		int minAgePreffered=Integer.parseInt(request.getParameter("ageMin"));
		int maxAgePreffered=Integer.parseInt(request.getParameter("ageMax"));
		String genderPreffered=request.getParameter("choice");
		
		User user=new User();
		user.setUsername(username);
		user.setMinAgePreffered(minAgePreffered);
		user.setMaxagePreffered(maxAgePreffered);
		user.setGenderPrefered(genderPreffered);
		FeaturesViewModel featuresViewModel = new FeaturesViewModel();
		featuresViewModel.settingsUpdate(user);
		
		try {
			response.getWriter().print(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

}

