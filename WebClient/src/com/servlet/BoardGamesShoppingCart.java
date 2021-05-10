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

public class BoardGamesShoppingCart extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {	
			String stringID = request.getParameter("id");
			if (stringID == null) {
				request.getRequestDispatcher("boardGames").forward(request, response);
				return;
			} 
			if (request.getSession().getAttribute("username") == null) {
				request.setAttribute("message", "You must identify to add items to the shopping cart");
				request.setAttribute("message_type", "warning");
				request.getRequestDispatcher("login").forward(request, response);
				return;
			}
			@SuppressWarnings("unchecked")
			Map<Integer, Integer> shoppingCart = (Map<Integer, Integer>) request.getSession().getAttribute("shoppingCart");
			if (shoppingCart == null) {
				shoppingCart = new HashMap<Integer, Integer>();
			}
			try {
				
				int id = Integer.parseInt(stringID);
				Integer value = shoppingCart.get(id);
				if (value == null) {
					shoppingCart.put(id, 1);
				} else {
					shoppingCart.put(id, value+1);
				}
				BoardGame boardGame = BoardGamesClient.findById(stringID);
				request.setAttribute("message", boardGame.getName()+ " added to the shopping cart");
				request.setAttribute("message_type", "success");
				request.getSession().setAttribute("shoppingCart", shoppingCart);
				request.getRequestDispatcher("boardGames").forward(request, response);

			} catch (NumberFormatException e) {
				request.getRequestDispatcher("boardGames").forward(request, response);
			}
    }
}
