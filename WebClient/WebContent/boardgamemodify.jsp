<%@page import="webService.BoardGame"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Board Game Project</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

	<div class="my-container background-black text-white">
		<div class="page-header text-center">
			<div>
				<h1>Board Game Project</h1>
				<h2 class="small-margin-bottom">Shop</h2>
			</div>

			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li><a href="index">Home</a></li>
					<li><a href="boardGames">Games available</a></li>
					<li><a href="purchase">Shopping cart</a></li>
					<%
						String role = (String) request.getSession().getAttribute("role");
						if (role != null && role.equals("admin")) {
					%>
					<li><a href="users">Users</a></li>
					<li class="active"><a href="boardGamesPrivate">Board Games (admin)</a></li>
					<%
						}
					%>
				</ul>
				<%
					String username = (String) request.getSession().getAttribute("username");
					if (username == null) {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="signup"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>
					<li class="active"><a href="login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
				<%
					} else {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li class="username"><p><%=username%></p></li>
					<li><a href="logout"><span
							class="glyphicon glyphicon-log-out"></span> Log out</a></li>
				</ul>
				<%
					}
				%>
			</div>
			</nav>
		</div>
		<%
			String message = (String) request.getAttribute("message");
			String message_type = (String) request.getAttribute("message_type");
			if (message != null) {
		%>
		<div class="container">
			<div class="<%=message_type%>">
				<span class="closebtn"
					onclick="this.parentElement.style.display='none';">&times;</span>
				<%=message%>
			</div>
		</div>
		<%
			}
		%>
		<div class="container">
			<%
				BoardGame boardGame = (BoardGame) request.getAttribute("boardGame");
			%>
			<form action="boardGameModify?id=<%=boardGame.getId()%>" method="post">
				<h3>Set board game's information</h3>

				<div class="form-group">
					<div class="form-group">
						<label for="name">Name:</label> <input type="text"
							class="form-control" id="name" name="name" value="<%=boardGame.getName() %>" required>
					</div>
					<div class="form-group">
						<label for="description">Description:</label> <input type="text"
							class="form-control" id="description" name="description" value="<%=boardGame.getDescription() %>" required>
					</div>
					<div class="form-group">
						<label for="imageURL">Board Game cover:</label> <input type="text"
							class="form-control" id="imageURL" name="imageURL" value="<%=boardGame.getImageURL() %>" required>
					</div>
					<div class="form-group">
						<label for="officialURL">Official page:</label> <input type="text"
							class="form-control" id="officialURL" name="officialURL" value="<%=boardGame.getOfficialURL() %>" required>
					</div>
					<div class="form-group">
						<label for="rulesURL">Rules page:</label> <input type="text"
							class="form-control" id="rulesURL" name="rulesURL" value="<%=boardGame.getRulesURL() %>" required>
					</div>
					<div class="form-group">
						<label for="price">Price (&#8364;):</label> <input type="number"
							class="form-control" id="price" name="price" value="<%=boardGame.getPrice() %>" required>
					</div>
					<div class="form-group">
						<label for="stock">Stock:</label> <input type="number"
							class="form-control" id="stock" name="stock" value="<%=boardGame.getStock() %>" required>
					</div>


					<button type="submit" class="btn btn-primary">Modify board game</button>
				</div>

			</form>
		</div>
	</div>
</body>
</html>