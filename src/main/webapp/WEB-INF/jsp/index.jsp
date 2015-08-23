<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container">
	<div class="row">
		
		<div class="col-sm-7">
			
			<h1>Cool pizzeria</h1>
			
			<h2>We bake the best pizza in the world!</h2>
			
			<h4>Our menu:</h4>
			
			<table class="table table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th class="col-sm-5">Name</th>
						<th class="col-sm-4">Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pizzas }" var="pizza">
						<tr>
							<td><c:out value="${pizza.name }"/> </td>
							<td><c:out value="${pizza.price }"/> </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<br>
			<h4>To buy some pizzas - just create new order and taste it!</h4>
			<c:url value="/order/pizza/select" var="createNewOrderUrl"/>
			<a class="btn btn-lg btn-primary" href="${createNewOrderUrl }">Create new order</a>
		</div>
	
	</div>

	<div class="row">
		<div class="col-sm-2">
			<br>		
			<p class="bg-info">
				Available logins: <br>
				Login: Paddy <br> 
				Pass: 2 <br> <br>
				Login: admin <br> 
				Pass: 1 <br>
			</p>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-2">
			<br>		
			<c:url value="/admin/pizza" var="adminUrl"/>
			<a href="${adminUrl }">Admin</a>
		</div>
	</div>
	
</div>

</body>
</html>