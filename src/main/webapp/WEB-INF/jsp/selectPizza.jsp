<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
		<h4 style="float:left; margin-right: 15px;">Hello, ${name }!</h4>
		<c:url var="logoutUrl" value="/logout"/>
	    <form action="${logoutUrl}" method="post">
	        <button type="submit" class="btn btn-default">Log out</button>
	        <sec:csrfInput/>
	    </form>
	</div>

	<div class="row">
		<div class="col-sm-5">
			<c:url value="/" var="homeUrl"/>
			<a href="${homeUrl }">Home</a> <br>
			
			<h2>Add pizzas to your cart:</h2>
		
			<table class="table table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th class="col-sm-5">Name</th>
						<th class="col-sm-4">Price</th>
						<th class="col-sm-3">Add</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pizzas }" var="pizza">
						<tr>
							<td><c:out value="${pizza.name }"/> </td>
							<td><c:out value="${pizza.price }"/> </td>
							<td>
								<form class="form-group" style="margin-bottom:0px" action="addtocart" method="post">
									<input type="hidden" name="pizzaId" value="${pizza.id }"/>
									<sec:csrfInput/>
									<button type="submit" class="btn btn-default">Add</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:url var="showCartUrl" value="/order/showcart"/>
			<a class="btn btn-default" href="${showCartUrl }">View cart</a>
			
		</div>
	</div>
	
	<sec:authorize access="hasRole('ADMIN')">
		<div class="row">
			<div class="col-sm-2">
				<br><br>
				<h4>Admin menu:</h4>
				<c:url value="/admin/pizza" var="adminUrl"/>
				<a href="${adminUrl }">Edit pizzas</a>
			</div>
		</div>
	</sec:authorize>
	
</div>	
</body>
</html>