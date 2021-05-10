<%@page import="webService.User"%>
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
		<form action = "userCreate" method= "get">
			<button type="submit" class="btn btn-default center-block">Create user</button>
		</form>
		<div class="container">
			<h3>Users of the application</h3>
			<table class="table table-dark">
				<tr>
					<th scope="col">Username</th>
					<th scope="col">Name</th>
					<th scope="col">Surname</th>
					<th scope="col">Role</th>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
				<%
					@SuppressWarnings("unchecked")
					List<User> users = (List<User>) request.getAttribute("users");
					for (User user : users) {
				%>
				<tr>
					<td><%=user.getUsername()%></td>
					<td><%=user.getName()%></td>
					<td><%=user.getSurname()%></td>
					<td><%=user.getRole()%></td>
					<td><form action="userView?id=<%=user.getId()%>" method="post"><button type="submit" class="btn btn-default btn-sm">View</button></form></td>
					<td><form action="userModify?id=<%=user.getId()%>" method="post"><button type="submit" class="btn btn-default btn-sm">Modify</button></form></td>
					<td><form action="userDelete?id=<%=user.getId()%>" method="post"><button type="submit" class="btn btn-danger btn-sm">Delete</button></form></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>