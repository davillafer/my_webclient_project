<%@page import="webService.BoardGame"%>
<%@page import="java.util.Map"%>
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
					<li class="active"><a href="users">Users</a></li>
					<li><a href="boardGamesPrivate">Board Games (admin)</a></li>
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
					<li><a href="login"><span
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
			<h3>Please, choose the number of board games that you want to buy</h3>
			<form class="form-horizontal" action="purchase" method="post">
				<%
					@SuppressWarnings("unchecked")
					Map<BoardGame, Integer> boardGames = (Map<BoardGame, Integer>) request.getAttribute("boardgames");
					for (Map.Entry<BoardGame, Integer> entry : boardGames.entrySet()) {
				%>
				<div class="form-group">
					<label class="control-label col-sm-2 text-align-left"
						for="<%=entry.getKey().getId()%>"><%=entry.getKey().getName()%></label>
					<div class="col-sm-1">
						<input type="number" min="1" class="form-control"
							name="<%=entry.getKey().getId()%>"
							id="<%=entry.getKey().getId()%>" value="<%=entry.getValue()%>">
					</div>
					<label class="control-label col-sm-1 text-align-left"
						for="<%=entry.getKey().getId()%>"><%=entry.getKey().getPrice()%>&#8364;</label>
					<div class="col-sm-1">
						<button type="submit" class="btn btn-danger" formaction="boardGamesShoppingCartDelete?id=<%=entry.getKey().getId()%>">Delete</button>
					</div>

				</div>

				<%
					}
				%>
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Continue</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>