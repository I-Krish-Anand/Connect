package com.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.features.FeaturesViewModel;

@WebServlet("/askedQuestions")
public class UpdateAskedQuestions extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		FeaturesViewModel featuresViewModel = new FeaturesViewModel();
		int question_id = Integer.parseInt(request.getParameter("question_id"));
		String username = (String) session.getAttribute("username");
		featuresViewModel.updateAskedQuestions(username, question_id);
		try {
			response.getWriter().print(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
