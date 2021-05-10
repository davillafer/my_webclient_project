package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.BoardGamesClient;
import client.UsersClient;

public class BoardGameView extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("boardGame", BoardGamesClient.findById(request.getParameter("id")));
		request.getRequestDispatcher("boardgameview.jsp").forward(request, response);
	}
}
