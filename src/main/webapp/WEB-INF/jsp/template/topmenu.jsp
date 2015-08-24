<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="row">
	<h4 style="float:left; margin-right: 15px;">Hello, ${customer.name }!</h4>
	<c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
        <button type="submit" class="btn btn-default">Log out</button>
        <sec:csrfInput/>
    </form>
</div>

<div class="row">
	<div class="col-sm-5">
		<c:url value="/" var="homeUrl"/>
		<a href="${homeUrl }" style="margin-right: 15px;">Home</a>
	</div>
</div>