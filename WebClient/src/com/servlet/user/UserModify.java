package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.UsersClient;
import webService.User;

public class UserModify extends HttpServlet {

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

		request.setAttribute("user", UsersClient.getUser(request.getParameter("id")));
		request.getRequestDispatcher("usermodify.jsp").forward(request, response);
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

		String parameter = request.getParameter("name");
		if (parameter == null) {
			doGet(request, response);
			return;
		}
		User user = UsersClient.getUser(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setSurname(request.getParameter("surname"));
		user.setRole(request.getParameter("role"));
		UsersClient.modify(user);
		request.setAttribute("message", "User succesfully modified");
		request.setAttribute("message_type", "success");
		request.getRequestDispatcher("users").forward(request, response);
	}
}