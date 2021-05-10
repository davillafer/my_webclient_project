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
import client.BoardGamesOrdersClient;
import client.OrdersClient;
import client.UsersClient;
import webService.BoardGame;
import webService.BoardGameOrder;
import webService.Order;

public class Confirm extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.setAttribute("message", "You must identify in order to be able to confirm your order");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}
		PersonalDataDTO pdd = (PersonalDataDTO) request.getSession().getAttribute("personalData");
		if (pdd == null) {
			request.setAttribute("message", "You tried accessing a private zone of the web");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("boardGames").forward(request, response);
			return;
		} 
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> shoppingCart = (Map<Integer, Integer>) request.getSession().getAttribute("shoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new HashMap<Integer, Integer>();
		}
		Map<BoardGame, Integer> boardGames = new HashMap<BoardGame, Integer>();
		double totalPrice = 0;
		for (Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()) {
			boardGames.put(BoardGamesClient.findById(String.valueOf(entry.getKey())), entry.getValue());
			double price = BoardGamesClient.findById(String.valueOf(entry.getKey())).getPrice();
			totalPrice += price * entry.getValue();
		}
		request.setAttribute("boardgames", boardGames);
		request.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("confirm.jsp").forward(request, response);
		


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.setAttribute("message", "You must identify in order to be able to confirm your order");
			request.setAttribute("message_type", "warning");
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}
		Order order = new Order();
		payment(request.getParameter("card"), request.getParameter("cvs"),
				request.getParameter("expiration"));
		order.setUserId(UsersClient.getUserByUsername((String) request.getSession().getAttribute("username")).getId());
		PersonalDataDTO pdd = (PersonalDataDTO) request.getSession().getAttribute("personalData");
		order.setCountry(pdd.getCountry());
		order.setProvince(pdd.getProvince());
		order.setLocation(pdd.getLocation());
		order.setPostalCode(pdd.getPostal_code());
		order.setAddress(pdd.getAddress());
		order.setStatus("ordered");
		int id = OrdersClient.orderId()+1;
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> shoppingCart = (Map<Integer, Integer>) request.getSession().getAttribute("shoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new HashMap<Integer, Integer>();
		}
		Map<BoardGame, Integer> boardGames = new HashMap<BoardGame, Integer>();
		double totalPrice = 0;
		for (Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()) {
			boardGames.put(BoardGamesClient.findById(String.valueOf(entry.getKey())), entry.getValue());
			double price = BoardGamesClient.findById(String.valueOf(entry.getKey())).getPrice();
			totalPrice += price * entry.getValue();
			BoardGameOrder bgo =new BoardGameOrder();
			bgo.setBoardGameId(entry.getKey());
			bgo.setOrderId(id);
			bgo.setNumber(entry.getValue());
			BoardGamesOrdersClient.create(bgo);
			
		}
		order.setPrice(totalPrice);
		OrdersClient.create(order);
		
		
		request.getSession().removeAttribute("shoppingCart");
		request.getSession().removeAttribute("personalData");
		request.setAttribute("message", "Your order has been succesfully completed");
		request.setAttribute("message_type", "success");
		request.getRequestDispatcher("boardGames").forward(request, response);
	}

	private void payment(String parameter, String parameter2, String parameter3) {
		
	}
}
