package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.UsersClient;
import webService.User;

public class Signup extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("username");
			String name = request.getParameter("firstname");
			String surname = request.getParameter("surname");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatPassword");
			if (!password.equals(repeatPassword)) {
				request.setAttribute("message", "Passwords do not match");
				request.setAttribute("message_type", "danger");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			} else {
				if (UsersClient.exists(username)) {
					request.setAttribute("message", "Username already used");
					request.setAttribute("message_type", "danger");
					request.getRequestDispatcher("signup.jsp").forward(request, response);

				} else {
					User user = new User();
					user.setName(name);
					user.setSurname(surname);
					user.setUsername(username);
					user.setPassword(password);
					user.setRole("user");
					if(UsersClient.create(user)){
						request.getSession().setAttribute("username", username);
						request.getSession().setAttribute("role", "user");
						request.setAttribute("message", "Welcome " + username);
						request.setAttribute("message_type", "success");
						request.getRequestDispatcher("boardGames").forward(request, response);
					} else {
						request.setAttribute("message", "User could not be created");
						request.setAttribute("message_type", "danger");
						request.getRequestDispatcher("signup.jsp").forward(request, response);
					}

				}
			}		
    }
}
