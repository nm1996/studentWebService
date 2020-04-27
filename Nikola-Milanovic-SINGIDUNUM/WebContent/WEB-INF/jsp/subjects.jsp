<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subjects</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
	function test() {
		return confirm("Are you sure you wanna delete?");
	}
</script>
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
				<li class="active"><a
					href="${pageContext.request.contextPath }/subjects">All
						subjects</a><br /></li>
				<li><a href="${pageContext.request.contextPath }/students">All
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
			<form>
				<input class="form-control" type="text" id="num" value="${num}">
				<button class="btn btn-success" type="submit">submit</button>
			</form>
			<br />
			<table class="table" border="1px solid black">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Semester</th>
					<th>YearOfStudy</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach var="subject" items="${subjects}">
					<tr>
						<td><c:out value="${subject.subjectId}" /></td>
						<td><c:out value="${subject.name}" /></td>
						<td><c:out value="${subject.description}" /></td>
						<td><c:out value="${subject.semestar}" /></td>
						<td><c:out value="${subject.yearOfStudy}" /></td>
						<td><a
							href="${pageContext.request.contextPath }/subjects/edit-subject/${subject.subjectId}">Edit
								subject</a></td>
						<td><a onclick="return test()"
							href="${pageContext.request.contextPath }/subjects/delete-subject/${subject.subjectId}">Delete
								subject</a></td>
					</tr>
				</c:forEach>
			</table>
			<c:forEach var="i" begin="1" end="${pages }" step="1">
				<a href="${pageContext.request.contextPath}/subjects/${i}/${num}">${i}</a>
			</c:forEach>
			<br /> <a
				href="${pageContext.request.contextPath }/subjects/create-subject">Create
				subject</a><br /> <a href="${pageContext.request.contextPath }/">Back
				to index</a>
		</div>
	</div>
	<script>
		window
				.addEventListener(
						'load',
						function() {
							document
									.getElementById("num")
									.addEventListener(
											"change",
											function(e) {
												window.location.href = "${pageContext.request.contextPath}/subjects/1/"
														+ document
																.getElementById("num").value;
											});
						});
	</script>
</body>
</html>