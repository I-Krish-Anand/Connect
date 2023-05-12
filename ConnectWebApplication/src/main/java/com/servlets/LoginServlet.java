package com.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.login.LoginViewModel;


@WebServlet ("/login")
public class LoginServlet extends HttpServlet {
	
	public void  doPost(HttpServletRequest request,HttpServletResponse response) {
		
		
		HttpSession session=request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		session.setAttribute("username", username);
		LoginViewModel login=new LoginViewModel();
		boolean flag=login.verifycredentials(username, password);
		
		try {
			response.getWriter().print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
