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
			<c:choose>
				<c:when test="${cart eq null}">
					<h4>Your order is empty. Please create new order</h4>
				</c:when>
				<c:otherwise>
					<h2>Your order successfully confirmed!</h2>
				
					<h2>Order contains:</h2>
				
					<table class="table table-bordered table-condensed table-striped">
						<thead>
							<tr>
								<th class="col-sm-5">Name</th>
								<th class="col-sm-3">Count</th>
								<th class="col-sm-4">Sum</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cart }" var="entry">
								<tr>
									<td><c:out value="${entry.key.name }"/> </td>
									<td><c:out value="${entry.value }"/> </td>
									<td><c:out value="${entry.key.price * entry.value }"/> </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<table class="table table-bordered table-condensed table-striped">
						<thead style="display: none;">
							<tr>
								<th class="col-sm-5"></th>
								<th class="col-sm-3"></th>
								<th class="col-sm-4"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="col-sm-8"><b>Order price</b></td>
								<td class="col-sm-4"><c:out value="${totalOrderPriceWoDiscount}"/> </td>
							</tr>
							<tr>
								<td class="col-sm-8"><b>Discount</b></td>
								<td class="col-sm-4"><c:out value="${orderDiscount }"/> </td>
							</tr>
							<tr>
								<td class="col-sm-8"><b>Total</b></td>
								<td class="col-sm-4"><c:out value="${totalOrderPriceWoDiscount - orderDiscount}"/> </td>
							</tr>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>

			<c:url var="newOrderUrl" value="/order/pizza/select"/>
			<a class="btn btn-primary" href="${newOrderUrl }">Create new order</a>
				
		</div>
	</div>
	
</div>	
</body>
</html>