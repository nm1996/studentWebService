<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Set Subject</title>
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
			<h2>${professor.firstName }${professor.lastName }
				${professor.title.name }</h2>
			<c:if test="${not empty subjects }">
				<form
					action="${pageContext.request.contextPath}/professors/do-set-subjects"
					method="post">
					<input type="hidden" value="${professor.professorId}"
						name="professor" /> <select class="form-control" name="subject">
						<c:forEach var="subject" items="${subjects}">
							<option value="${subject.subjectId}">${subject.name }</option>
						</c:forEach>
					</select> <input class="form-control" type="submit" value="Set subjects" />
				</form>
			</c:if>
			<c:if test="${empty subjects }">
				<div class="alert alert-danger">
					<strong>There are not new subjects for submit.</strong>
				</div>
			</c:if>

		</div>
	</div>
</body>
</html>