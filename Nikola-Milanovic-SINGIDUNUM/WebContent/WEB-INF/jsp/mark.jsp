<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mark</title>
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
				<li><a href="${pageContext.request.contextPath }/exams">All
						exams</a><br /></li>
				<li class="active"><a
					href="${pageContext.request.contextPath }/marks">All marks</a><br /></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table" border="1px solid black">
				<tr>
					<th>ID</th>
					<th>Mark</th>
					<th>Subject</th>
					<th>Professor</th>
					<th>Exam</th>
					<th>Student</th>
				</tr>
				<tr>
					<td><c:out value="${mark.markId}" /></td>
					<td><c:out value="${mark.mark}" /></td>
					<td><c:out value="${mark.subject.name}" /></td>
					<td><c:out
							value="${mark.exam.professor.firstName} ${mark.exam.professor.lastName}" />
					</td>
					<td><c:out value="${mark.exam.examDate}" /></td>
					<td><c:out value="${mark.student.firstName}" /> <c:out
							value="${mark.student.lastName}" /></td>
			</table>

			<a href="${pageContext.request.contextPath }/marks">Back</a>
		</div>
	</div>
</body>
</html>