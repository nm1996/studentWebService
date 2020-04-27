<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Professor</title>
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
			<table class="table" border="1px solid black">
				<tr>
					<th>ID</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>City</th>
					<th>Address</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Reelaction date</th>
					<th>Title</th>
				</tr>
				<tr>
					<td><c:out value="${professor.professorId}" /></td>
					<td><c:out value="${professor.firstName}" /></td>
					<td><c:out value="${professor.lastName}" /></td>
					<td><c:out value="${professor.city.name}" /></td>
					<td><c:out value="${professor.address}" /></td>
					<td><c:out value="${professor.email}" /></td>
					<td><c:out value="${professor.phone}" /></td>
					<td><c:out value="${professor.reelectiondate}" /></td>
					<td><c:out value="${professor.title.name}" /></td>
				</tr>
			</table>
			<br />
			<table class="table" border="1px solid black">
				<tr>
					<th>Subject</th>
					<th>Semestar</th>
					<th>YearOfStudy</th>
				</tr>
				<c:forEach var="subject" items="${subjects}">
					<tr>
						<td>${subject.name }</td>
						<td>${subject.semestar }</td>
						<td>${subject.yearOfStudy }</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<table class="table" border="1px solid black">
				<tr>
					<th>ExamDate</th>
					<th>Subject</th>
					<th>Professor</th>
				</tr>
				<c:forEach var="exam" items="${exams}">
					<tr>
						<td>${exam.examDate }</td>
						<td>${exam.subject.name }</td>
						<td>${exam.professor.firstName }${exam.professor.lastName }</td>
					</tr>
				</c:forEach>
			</table>

			<a href="${pageContext.request.contextPath }/professors">Back</a>
		</div>
	</div>
</body>
</html>