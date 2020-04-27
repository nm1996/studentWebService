<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students</title>
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
			<form>
				<input class="form-control" type="text" id="num" value="${num}">
				<button class="btn btn-success" type="submit">submit</button>
			</form>
			<br />
			<table class="table" border="1px solid black">
				<tr>
					<th>ID</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Index</th>
					<th>City</th>
					<th>Address</th>
					<th>CurrentYearOfStudy</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Finance</th>
					<th>Set subject</th>
					<th>Set exam</th>
					<th>Details</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach var="student" items="${students}">
					<tr>
						<td><c:out value="${student.studentId}" /></td>
						<td><c:out value="${student.firstName}" /></td>
						<td><c:out value="${student.lastName}" /></td>
						<td><c:out value="${student.indexNumber}" /></td>
						<td><c:out value="${student.city.name}" /></td>
						<td><c:out value="${student.address}" /></td>
						<td><c:out value="${student.currentYearOfStudy}" /></td>
						<td><c:out value="${student.email}" /></td>
						<td><c:out value="${student.phone}" /></td>
						<td><c:out value="${student.finance}" /></td>
						<td><a
							href="${pageContext.request.contextPath }/students/${student.studentId}/set-subjects">Set
								subject</a></td>
						<td><a
							href="${pageContext.request.contextPath }/students/${student.studentId}/set-exams">Set
								exam</a></td>
						<td><a
							href="${pageContext.request.contextPath }/students/${student.studentId}">Details
								of student</a></td>
						<td><a
							href="${pageContext.request.contextPath }/students/edit-student/${student.studentId}">Edit
								student</a></td>
						<td><a onclick="return test()"
							href="${pageContext.request.contextPath }/students/delete-student/${student.studentId}">Delete
								student</a></td>
					</tr>
				</c:forEach>
			</table>
			<c:forEach var="i" begin="1" end="${pages }" step="1">
				<a href="${pageContext.request.contextPath}/students/${i}/${num}">${i}</a>
			</c:forEach>
			<br /> <a
				href="${pageContext.request.contextPath }/students/create-student">Create
				student</a><br /> <a href="${pageContext.request.contextPath }/">Back
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
												window.location.href = "${pageContext.request.contextPath}/students/1/"
														+ document
																.getElementById("num").value;
											});
						});
	</script>
</body>
</html>