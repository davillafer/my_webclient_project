package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.PersonalDataDTO;
import client.BoardGamesClient;

public class PersonalData extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.setAttribute("message",
					"You must identify in order to be able to introduce your address and payment information");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> shoppingCart = (Map<Integer, Integer>) request.getSession().getAttribute("shoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new HashMap<Integer, Integer>();
		}
		double totalPrice = 0;
		for (Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()) {
			double price = BoardGamesClient.findById(String.valueOf(entry.getKey())).getPrice();
			totalPrice += price * entry.getValue();
		}
		request.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("personaldata.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.setAttribute("message",
					"You must identify in order to be able to introduce your address and payment information");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}
		request.getSession().setAttribute("personalData",
				new PersonalDataDTO(request.getParameter("country"), request.getParameter("province"),
						request.getParameter("location"), request.getParameter("postal_code"),
						request.getParameter("address"), request.getParameter("card"), request.getParameter("cvs"),
						request.getParameter("expiration")));
		response.sendRedirect(request.getContextPath() + "/confirm");
	}
}