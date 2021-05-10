<%@page import="webService.BoardGame"%>
<%@page import="java.util.List"%>
<%@page import="DTO.PersonalDataDTO"%>
<%@page import="java.util.Map"%>
<%@page import="util.Conversor"%>
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

			<h3>Please confirm your order</h3>
			<form class="form-horizontal" action="confirm" method="post">
				<div class="col-sm-4">
					<%
						@SuppressWarnings("unchecked")
						Map<BoardGame, Integer> boardGames = (Map<BoardGame, Integer>) request.getAttribute("boardgames");
						for (Map.Entry<BoardGame, Integer> entry : boardGames.entrySet()) {
					%>
					<div class="form-group">
						<label class="control-label col-sm-5 text-align-left"
							for="<%=entry.getKey().getId()%>"><%=entry.getKey().getName()%></label>
						<div class="col-sm-3">
							<input type="text" class="form-control"
								name="<%=entry.getKey().getId()%>"
								id="<%=entry.getKey().getId()%>" value="<%=entry.getValue()%>"
								disabled>
						</div>
						<label class="control-label col-sm-3 text-align-left"
							for="<%=entry.getKey().getId()%>"><%=entry.getKey().getPrice()%></label>
					</div>
					<%
						}
					%>
										<h4>
						Total <%=String.valueOf(request.getAttribute("totalPrice"))%> &#8364; (<%= String.valueOf(Conversor.conversedPrice((double)request.getAttribute("totalPrice"), "GBP"))%>&#163;)
					</h4>
					<button type="submit" class="btn btn-warning" formaction="purchase"
						formmethod="get">Modify shopping cart</button>
				</div>
				<%
					PersonalDataDTO pdd = (PersonalDataDTO) request.getSession().getAttribute("personalData");
				%>
				<div class="col-sm-4">

					<div class="form-group">

						<label class="control-label col-sm-4 text-align-left"
							for="country">Country:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="country"
								id="country" value="<%=pdd.getCountry()%>" disabled>
						</div>

					</div>
					<div class="form-group">
						<label class="control-label col-sm-4 text-align-left"
							for="province">Province:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="province"
								id="province" value="<%=pdd.getProvince()%>" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4 text-align-left"
							for="location">Location:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="location"
								id="location" value="<%=pdd.getLocation()%>" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4 text-align-left"
							for="postal_code">Postal code:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="postal_code"
								id="postal_code" value="<%=pdd.getPostal_code()%>" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4 text-align-left"
							for="address">Address:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="address"
								id="address" value="<%=pdd.getAddress()%>" disabled>
						</div>
					</div>
					<button type="submit" class="btn btn-warning"
						formaction="personalData" formmethod="get">Modify
						personal data</button>
					<button type="submit" class="btn btn-primary center-block width-100 medium-margin-top">Confirm</button>

				</div>
				
				<div class="col-sm-4">
					<div class="form-group">
						<label class="control-label col-sm-4 text-align-left" for="card">Card
							ID:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="card" id="card"
								value="<%=pdd.getCard()%>" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4 text-align-left" for="cvs">CVS:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="cvs" id="cvs"
								value="<%=pdd.getCvs()%>" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4 text-align-left"
							for="expiration">Expiration date:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="expiration"
								id="expiration" value="<%=pdd.getExpiration()%>">
						</div>
					</div>
					<button type="submit" class="btn btn-warning"
						formaction="personalData" formmethod="get">Modify payment
						data</button>

				</div>



			</form>
		</div>
	</div>
</body>
</html>