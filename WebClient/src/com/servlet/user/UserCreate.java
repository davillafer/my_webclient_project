package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.UsersClient;
import webService.User;

public class UserCreate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = (String) request.getSession().getAttribute("role");
		if (role == null || !role.equals("admin")) {
			request.setAttribute("message", "You can't access that page");
			request.setAttribute("message_type", "danger");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}

		request.getRequestDispatcher("usercreate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = (String) request.getSession().getAttribute("role");
		if (role == null || !role.equals("admin")) {
			request.setAttribute("message", "You can't access that page");
			request.setAttribute("message_type", "danger");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}

		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setSurname(request.getParameter("surname"));
		user.setUsername(request.getParameter("username"));
		user.setRole(request.getParameter("role"));
		if (UsersClient.exists(request.getParameter("username"))) {
			request.setAttribute("message", "Username already used");
			request.setAttribute("message_type", "danger");
			doGet(request, response);
		} else {
			UsersClient.create(user);
			request.setAttribute("message", "User succesfully created");
			request.setAttribute("message_type", "success");
			request.getRequestDispatcher("users").forward(request, response);
		}
	}
}
