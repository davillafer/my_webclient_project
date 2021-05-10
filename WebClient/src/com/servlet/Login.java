package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.UsersClient;
import webService.User;

public class Login  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("login.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = UsersClient.authenticate(username, password);
			if (request.getAttribute("message_type") != null) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			if (user == null) {
				request.setAttribute("message", "Wrong credentials");
				request.setAttribute("message_type", "danger");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			} 
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("role", user.getRole());
			request.setAttribute("message", "Welcome " + user.getUsername());
			request.setAttribute("message_type", "success");
			request.getRequestDispatcher("boardGames").forward(request, response);
			
    }
}
