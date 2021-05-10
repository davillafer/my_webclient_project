package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("role");
			request.getSession().removeAttribute("shoppingCart");
			request.getSession().removeAttribute("personalData");
			request.getRequestDispatcher("boardGames").forward(request, response);

    }

}
