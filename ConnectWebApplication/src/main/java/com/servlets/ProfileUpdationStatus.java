package com.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.features.FeaturesViewModel;

@WebServlet("/ProfileUpdationStatus")
public class ProfileUpdationStatus extends HttpServlet {
	
public void  doPost(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		String username= (String) session.getAttribute("username");
		FeaturesViewModel featuresViewModel=new FeaturesViewModel();
        try {
			response.getWriter().print(featuresViewModel.profileUpdationStatus(username));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}

}
