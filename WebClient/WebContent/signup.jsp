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
					<li class="active"><a href="signup"><span
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
			<h3>Please introduce your credentials</h3>
			<form action="signup" method="post">
				<div class="form-group">
					<label for="usr">Username:</label> <input type="text"
						class="form-control" name="username" id="usr" required>
				</div>
				<div class="form-group">
					<label for="firstname">Name:</label> <input type="text"
						class="form-control" name="firstname" id="firstname" required>
				</div>
				<div class="form-group">
					<label for="surname">Surname:</label> <input type="text"
						class="form-control" name="surname" id="surname" required>
				</div>
				<div class="form-group">
					<label for="pass">Password:</label> <input type="password"
						class="form-control" name="password" id="pass" required>
				</div>
				<div class="form-group">
					<label for="pass">Repeat password:</label> <input type="password"
						class="form-control" name="repeatPassword" id="pass" required>
				</div>
				<button type="submit" class="btn btn-primary">Sign in</button>
			</form>
		</div>
	</div>
</body>
</html>