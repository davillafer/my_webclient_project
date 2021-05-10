package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.BoardGamesClient;

public class BoardGames extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("boardgames", BoardGamesClient.getBoardGames());
			request.getRequestDispatcher("boardgames.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
			String name = request.getParameter("name");
			if(name != null) {
				request.setAttribute("boardgames", BoardGamesClient.getBoardGamesByContainsName(name));
			} else {
				request.setAttribute("boardgames", BoardGamesClient.getBoardGames());
			}
			
			request.getRequestDispatcher("boardgames.jsp").forward(request, response);
    }
	
}

