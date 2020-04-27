<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create student</title>
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
				<li class="active"><a
					href="${pageContext.request.contextPath }/students">All
						students</a><br /></li>
				<li><a href="${pageContext.request.contextPath }/professors">All
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
				action="${pageContext.request.contextPath}/students/do-create-student"
				method="post">
				FirstName: <input class="form-control" type="text" name="firstName" />
				<br /> LastName: <input class="form-control" type="text"
					name="lastName" /> <br /> Email: <input class="form-control"
					type="email" name="email" /> <br /> Phone: <input
					class="form-control" type="text" name="phone" /> <br /> Address: <input
					class="form-control" type="text" name="address" /> <br />
				CurrentYearOfStudy: <select class="form-control"
					name="currentYearOfStudy">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select> <br /> Index: <input class="form-control" type="text"
					name="indexNumber" /> <br /> City: <select class="form-control"
					name="city.cityId">
					<c:forEach var="city" items="${cities }">
						<option value="${city.cityId}">${city.name }</option>
					</c:forEach>
				</select> <br /> Finance: <select class="form-control" name="finance">
					<option value="budget">Budget</option>
					<option value="self-finance">Self-finance</option>
				</select> <br /> <input class="form-control" type="submit"
					value="Insert student" />
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