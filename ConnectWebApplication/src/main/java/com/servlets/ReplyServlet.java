package com.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.features.FeaturesViewModel;


@WebServlet ("/reply")
public class ReplyServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		FeaturesViewModel featuresViewModel = new FeaturesViewModel();
		int chosenAnswer=Integer.parseInt(request.getParameter("myAnswer"));
		int answerkey=Integer.parseInt(request.getParameter("answer"));
		int question_id=Integer.parseInt(request.getParameter("question_id"));
		String questioner=request.getParameter("questioner");
		String username=(String) session.getAttribute("username");
		System.out.println(username+" "+answerkey+" "+chosenAnswer+" "+questioner);
		int result=featuresViewModel.reply(questioner, username, chosenAnswer, answerkey,question_id);
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
