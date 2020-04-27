<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create exam</title>
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
				<li><a href="${pageContext.request.contextPath }/professors">All
						professors</a><br /></li>
				<li class="active"><a
					href="${pageContext.request.contextPath }/exams">All exams</a><br /></li>
				<li><a href="${pageContext.request.contextPath }/marks">All
						marks</a><br /></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<c:if test="${not empty subjects }">
				<form
					action="${pageContext.request.contextPath}/exams/do-create-exam"
					method="post">
					Date: <input class="form-control" type="date" name="examDate" /> <br />
					Subject and Professor: <select class="form-control" name="ids">
						<c:forEach var="subject" items="${subjects }"><c:forEach var="professor" items="${subject.professors}">
							<option value="${subject.subjectId}_${professor.professorId}">
							${subject.name }  - ${professor.firstName } ${professor.lastName}
								</option>
							</c:forEach>
						</c:forEach>
					</select> <br />
					<!--   Professor: <select class="form-control"
						name="professor.professorId">
						<c:forEach var="professor" items="${professors }">
							<option value="${professor.professorId}">${professor.firstName }
								${professor.lastName }</option>
						</c:forEach>
					</select> 
					-->
					<br /> <br /> <input class="form-control" type="submit"
						value="Insert exam" />
				</form>
			</c:if>
			<c:if test="${empty subjects }">
				<div class="alert alert-danger">
					<strong> All subjects have scheduled exams or there are
						not subjects. </strong>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>