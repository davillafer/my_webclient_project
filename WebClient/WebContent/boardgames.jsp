<%@page import="webService.BoardGame"%>
<%@page import="util.Conversor"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
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
					<li class="active"><a href="boardGames">Games available</a></li>
					<li><a href="purchase">Shopping cart</a></li>
					<%
						String role = (String) request.getSession().getAttribute("role");
						if (role != null && role.equals("admin")) {
					%>
					<li><a href="users">Users</a></li>
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
		<div class="container small-margin-bottom">
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
			<form action="boardGames" method="post">
				<div class="form-group">
					<input type="text" class="form-control" name="name">
				</div>
				<button type="submit" class="btn btn-primary">Search</button>
			</form>
		</div>

		<div class="container">
			<div class="center">
				<h3>Our board games</h3>
			</div>
			<%
				int counter = 0;
				@SuppressWarnings("unchecked")
				List<BoardGame> boardGames = (List<BoardGame>) request.getAttribute("boardgames");
				for (BoardGame boardGame : boardGames) {
					counter++;
			%>
			<%
				if (counter == 1) {
			%>
			<div class="row">
				<div class="col-sm-3 panel-body center">
					<form method="post"
						action="boardGameView?id=<%=boardGame.getId()%>" class="inline">
						<button type="submit" name="submit_param" value="submit_value"
							class="title_button">
							<%=boardGame.getName()%>
						</button>
					</form>
					<img class="boardgame-img" alt="<%=boardGame.getName()%>"
						src="<%=boardGame.getImageURL()%>">
					<p><%=String.valueOf(boardGame.getPrice())%>&#8364; (<%=Conversor.conversedPrice(boardGame.getPrice(), "GBP")%>&#163;)
					</p>
					<form action="boardGamesShoppingCart?id=<%=boardGame.getId()%>"
						method="post">
						<button type="submit" class="btn btn-success">Add to
							shopping cart</button>
					</form>
				</div>
				<%
					}
				%>
				<%
					if (counter == 2 || counter == 3) {
				%>
				<div class="col-sm-3 panel-body center">
					<form method="post"
						action="boardGameView?id=<%=boardGame.getId()%>" class="inline">
						<button type="submit" name="submit_param" value="submit_value"
							class="title_button">
							<%=boardGame.getName()%>
						</button>
					</form>
					<img class="boardgame-img" alt="<%=boardGame.getName()%>"
						src="<%=boardGame.getImageURL()%>">
					<p><%=String.valueOf(boardGame.getPrice())%>&#8364; (<%=Conversor.conversedPrice(boardGame.getPrice(), "GBP")%>&#163;)
					</p>
					<form action="boardGamesShoppingCart?id=<%=boardGame.getId()%>"
						method="post">
						<button type="submit" class="btn btn-success">Add to
							shopping cart</button>
					</form>
				</div>
				<%
					}
				%>
				<%
					if (counter == 4) {
				%>
				<div class="col-sm-3 panel-body center">
					<form method="post"
						action="boardGameView?id=<%=boardGame.getId()%>" class="inline">
						<button type="submit" name="submit_param" value="submit_value"
							class="title_button">
							<%=boardGame.getName()%>
						</button>
					</form>
					<img class="boardgame-img" alt="<%=boardGame.getName()%>"
						src="<%=boardGame.getImageURL()%>">
					<p><%=String.valueOf(boardGame.getPrice())%>&#8364; (<%=Conversor.conversedPrice(boardGame.getPrice(), "GBP")%>&#163;)
					</p>
					<form action="boardGamesShoppingCart?id=<%=boardGame.getId()%>"
						method="post">
						<button type="submit" class="btn btn-success">Add to
							shopping cart</button>
					</form>
				</div>
			</div>
			<%
				counter = 0;
					}
			%>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>