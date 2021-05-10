package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.BoardGamesClient;
import webService.BoardGame;

public class Purchase extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.setAttribute("message", "You must identify in order to be able to purchase items");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> shoppingCart = (Map<Integer, Integer>) request.getSession().getAttribute("shoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new HashMap<Integer, Integer>();
		}
		Map<BoardGame, Integer> boardGames = new HashMap<BoardGame, Integer>();
		for (Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()) {
			boardGames.put(BoardGamesClient.findById(String.valueOf(entry.getKey())), entry.getValue());
		}
		if (boardGames.isEmpty()) {
			request.setAttribute("message", "Please, add games to the shopping cart");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("boardGames").forward(request, response);
			return;
		}
		request.setAttribute("boardgames", boardGames);
		request.getRequestDispatcher("purchase.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> parameters = request.getParameterMap();
		if (request.getSession().getAttribute("username") == null) {
			request.setAttribute("message", "You must identify in order to be able to purchase items");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}
		@SuppressWarnings("unchecked")

		Map<Integer, Integer> shoppingCart = (Map<Integer, Integer>) request.getSession().getAttribute("shoppingCart");
		try {
			if (shoppingCart == null) {
				shoppingCart = new HashMap<Integer, Integer>();
			}
			for (Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()) {
				Integer key = entry.getKey();
				String[] values = parameters.get(String.valueOf(key));
				Integer value = Integer.parseInt(values[0]);
				shoppingCart.replace(key, value);
			}
			request.getSession().setAttribute("shoppingCart", shoppingCart);
			response.sendRedirect(request.getContextPath()+"/personalData");
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/personalData");
		}

	}
}
