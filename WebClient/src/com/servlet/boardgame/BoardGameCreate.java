package com.servlet.boardgame;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.BoardGamesClient;
import webService.BoardGame;

public class BoardGameCreate extends HttpServlet {

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

		request.getRequestDispatcher("boardgamecreate.jsp").forward(request, response);
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

		BoardGame boardGame = new BoardGame();
		boardGame.setName(request.getParameter("name"));
		boardGame.setDescription(request.getParameter("description"));
		boardGame.setImageURL(request.getParameter("imageURL"));
		boardGame.setOfficialURL(request.getParameter("officialURL"));
		boardGame.setRulesURL(request.getParameter("rulesURL"));
		try {
			boardGame.setPrice(Double.parseDouble(request.getParameter("price")));
		} catch (NumberFormatException e1) {
			boardGame.setPrice(0);
		}
		try {
			boardGame.setStock(Integer.parseInt(request.getParameter("stock")));
		} catch (NumberFormatException e) {
			boardGame.setStock(0);
		}
		BoardGamesClient.create(boardGame);
		request.setAttribute("message", "Board game succesfully created");
		request.setAttribute("message_type", "success");
		request.getRequestDispatcher("boardGamesPrivate").forward(request, response);
	}
}
