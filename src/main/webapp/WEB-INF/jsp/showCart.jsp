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
		<div class="col-sm-5">
			<h2>Pizzas in Cart:</h2>

			<p>
				<c:url var="selectPizzaUrl" value="/order/pizza/select"/>
				<a class="btn btn-primary" href="${selectPizzaUrl }">Continue choosing pizzas</a>
			</p>
		
			<c:choose>
				<c:when test="${sessionScope.cart eq null}">
					<h4>Your cart is empty</h4>
				</c:when>
				<c:otherwise>
					<table class="table table-bordered table-condensed table-striped">
						<thead>
							<tr>
								<th class="col-sm-5">Name</th>
								<th class="col-sm-3">Count</th>
								<th class="col-sm-4">Sum</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.cart }" var="entry">
								<tr>
									<td><c:out value="${entry.key.name }"/> </td>
									<td><c:out value="${entry.value }"/> </td>
									<td><c:out value="${entry.key.price * entry.value}"/> </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<table class="table table-bordered table-condensed table-striped">
						<tbody>
							<tr>
								<td class="col-sm-8"><b>Order price</b></td>
								<td class="col-sm-4"><c:out value="${sessionScope.totalOrderPriceWoDiscount}"/> </td>
							</tr>
							<tr>
								<td class="col-sm-8"><b>Discount</b></td>
								<td class="col-sm-4"><c:out value="${sessionScope.orderDiscount }"/> </td>
							</tr>
							<tr>
								<td class="col-sm-8"><b>Total</b></td>
								<td class="col-sm-4"><c:out value="${sessionScope.totalOrderPriceWoDiscount - sessionScope.orderDiscount}"/> </td>
							</tr>
						</tbody>
					</table>
						
					<form action="checkout" method="post" >
						<button type="submit" class="btn btn-default">Checkout</button>
					</form>
				
				</c:otherwise>
			</c:choose>
		
			
			
		</div>
	</div>
	
</div>	
</body>
</html>