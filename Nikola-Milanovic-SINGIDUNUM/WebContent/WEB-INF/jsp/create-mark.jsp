<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create mark</title>
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
			<c:if test="${not empty exams }">
				<form action="${pageContext.request.contextPath}/marks/create-mark"
					method="post">
					Exam: <select class="form-control" name="examId">
						<c:forEach var="exam" items="${exams }">
							<option value="${exam.examId}">${exam.subject.name }
								${exam.examDate }</option>
						</c:forEach>
					</select> <br /> <input class="form-control" type="submit"
						value="Select exam" />
				</form>
			</c:if>
			<c:if test="${empty students }">
				<c:if test="${empty exams }">
					<div class="alert alert-danger">
							<strong>All exams have marks, or there are not finished exams yet.</strong>
						</div>
				</c:if>
			</c:if>
			<c:if test="${not empty students }">
				<form
					action="${pageContext.request.contextPath}/marks/do-create-mark"
					method="post">
					<input class="form-control" type="hidden" name="subject.subjectId"
						value="${subject.subjectId }" /> <input class="form-control"
						type="hidden" name="exam.examId" value="${exam.examId }" /> <select
						class="form-control" name="mark">
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select> <select class="form-control" name="student.studentId">
						<c:forEach var="student" items="${students }">
							<option value="${student.studentId}">${student.firstName }
								${student.lastName }</option>
						</c:forEach>
					</select> <input class="form-control" type="submit" value="Insert mark" />
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>