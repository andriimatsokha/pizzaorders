<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
	<c:import url="template/topmenu.jsp"/>
	
	<div class="row">
		<div class="col-sm-5">
			
			<h4>Edit pizzas</h4>
			
			
			<table class="table table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th class="col-sm-5">Name</th>
						<th class="col-sm-4">Price</th>
						<th class="col-sm-4">Type</th>
						<th class="col-sm-3">Edit</th>
						<th class="col-sm-3">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pizzas }" var="pizza">
						<tr>
							<td><c:out value="${pizza.name }"/> </td>
							<td><c:out value="${pizza.price }"/> </td>
							<td><c:out value="${pizza.type }"/> </td>
							<td>
								<c:url var="editPizzaUrl" value="/admin/pizza/edit"/>
								<form class="form-group" action="${editPizzaUrl }" style="margin-bottom:0px">
									<input type="hidden" name="pizzaId" value="${pizza.id }"/>
									<button type="submit" class="btn btn-default">Edit</button>
								</form>
							</td>
							<td>
								<c:url var="delPizzaUrl" value="/admin/pizza/delete"/>
								<form class="form-group" action="${delPizzaUrl }" style="margin-bottom:0px" method="post">
									<input type="hidden" name="pizzaId" value="${pizza.id }"/>
									<sec:csrfInput />
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:url var="addPizzaUrl" value="/admin/pizza/add"/>
			<a href="${addPizzaUrl }" class="btn btn-default">Add new pizza</a>
			
		</div>
	
	</div>
	
</div>

</body>
</html>