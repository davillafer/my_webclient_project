package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.UsersClient;

public class UserDelete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = (String) request.getSession().getAttribute("role");
		if (role == null || !role.equals("admin")) {
			request.setAttribute("message", "You can't access that page");
			request.setAttribute("message_type", "danger");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}

		boolean result = UsersClient.removeUser(request.getParameter("id"));
		if (result) {
			request.setAttribute("message", "User deleted");
			request.setAttribute("message_type", "success");
		} else {
			request.setAttribute("message", "User wasn't deleted");
			request.setAttribute("message_type", "danger");
		}
		request.getRequestDispatcher("users").forward(request, response);
	}
}
