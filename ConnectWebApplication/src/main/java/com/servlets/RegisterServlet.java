package com.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.register.RegisterViewModel;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session=request.getSession();
		RegisterViewModel registerViewModel = new RegisterViewModel();
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String phonenumber = request.getParameter("phonenumber");
		int age = Integer.parseInt(request.getParameter("age"));
		String geneder = request.getParameter("gender");
		session.setAttribute("username", username);

		if (registerViewModel.validateUserName(username) && registerViewModel.validatePassword(password)) {
			registerViewModel.createAccount(name, phonenumber, username, password, age, geneder);
			try {
				response.getWriter().print(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
