package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardGamesShoppingCartDelete  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {	
			String stringID = request.getParameter("id");
			if (stringID == null) {
				request.getRequestDispatcher("boardGames").forward(request, response);
				return;
			} 
			if (request.getSession().getAttribute("username") == null) {
				request.setAttribute("message", "You must identify to remove items from the shopping cart");
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
				shoppingCart.remove(id);
				request.getSession().setAttribute("shoppingCart", shoppingCart);
				response.sendRedirect(request.getContextPath()+"/purchase");

			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath()+"/purchase");
			}
    }
}