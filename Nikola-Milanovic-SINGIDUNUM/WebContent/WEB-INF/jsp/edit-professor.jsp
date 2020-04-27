<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit professor</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Student web service</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath }/cities">All
						cities</a></li>
				<li><a href="${pageContext.request.contextPath }/titles">All
						titles</a></li>
				<li><a href="${pageContext.request.contextPath }/subjects">All
						subjects</a><br /></li>
				<li><a href="${pageContext.request.contextPath }/students">All
						students</a><br /></li>
				<li class="active"><a
					href="${pageContext.request.contextPath }/professors">All
						professors</a><br /></li>
				<li><a href="${pageContext.request.contextPath }/exams">All
						exams</a><br /></li>
				<li><a href="${pageContext.request.contextPath }/marks">All
						marks</a><br /></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<form
				action="${pageContext.request.contextPath}/professors/do-edit-professor"
				method="post">
				<input type="hidden" name="professorId"
					value="${professor.professorId }" /> FirstName: <input
					class="form-control" type="text" name="firstName"
					value="${professor.firstName }" /> <br /> LastName: <input
					class="form-control" type="text" name="lastName"
					value="${professor.lastName }" /> <br /> Email: <input
					class="form-control" type="email" name="email"
					value="${professor.email }" /> <br /> Phone: <input
					class="form-control" type="text" name="phone"
					value="${professor.phone }" /> <br /> Address: <input
					class="form-control" type="text" name="address"
					value="${professor.address }" /> <br /> Reelectiondate: <input
					class="form-control" type="date" name="reelectiondate"
					value="${professor.reelectiondate }" /> <br /> City: <select
					class="form-control" name="city.cityId">
					<c:forEach var="city" items="${cities }">
						<option value="${city.cityId}">${city.name }</option>
					</c:forEach>
				</select> <br /> Title: <select class="form-control" name="title.titleId">
					<c:forEach var="title" items="${titles }">
						<option value="${title.titleId}">${title.name }</option>
					</c:forEach>
				</select> <br /> <input class="form-control" type="submit"
					value="Edit professor" />
			</form>

			<c:if test="${not empty messages}">
				<c:forEach var="message" items="${messages }">
					<div class="alert alert-danger">
						<strong><c:out value="${message}" /></strong>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</body>
</html>