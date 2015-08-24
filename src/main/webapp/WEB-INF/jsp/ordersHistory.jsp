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

	<c:import url="template/topmenu.jsp"/>

	<div class="row">
		<div class="col-sm-5">
			
			<h2>Orders history:</h2>
			
			<c:forEach var="order" items="${orders }">
			
				<h4>Order #${order.id } Date: ${order.createDate } </h4>
				<sec:authorize access="hasRole('ADMIN')">
					Customer ${order.customer.name } (#${order.customer.id })
				</sec:authorize>
				<table class="table table-bordered table-condensed table-striped">
					<thead>
						<tr>
							<th class="col-sm-1">#</th>
							<th class="col-sm-5">Name</th>
							<th class="col-sm-3">Count</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${order.pizzas }" var="entry" varStatus="stat">
							<tr>
								<td><c:out value="${stat.count }"/> </td>
								<td><c:out value="${entry.key.name }"/> </td>
								<td><c:out value="${entry.value }"/> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</c:forEach>
		
			
			
		</div>
		
	</div>
	
</div>	
</body>
</html>