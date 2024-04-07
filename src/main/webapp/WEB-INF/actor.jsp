<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Actor</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>

<body class="mb-7">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/MVCFilmSite">JSP</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/MVCFilmSite">Home</a></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/MVCFilmSite/films.do">Films</a></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/MVCFilmSite/actors.do">Actors</a></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/MVCFilmSite/about.do">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<main>

		<div class="container mt-5">
			<h1>Actor</h1>

			<c:if test="${! empty message}">
				<p>${message}</p>
			</c:if>

			<c:if test="${! empty error}">
				<p>${error}</p>
			</c:if>

		</div>


		<div class="container">
			<div class="row">
				<div class="col"></div>
			</div>
		</div>

		<c:if test="${empty actor}">
			<c:choose>
				<c:when test="${add}">
					<div class="container mt-5">
						<h2>Add Actor</h2>
						<form action="addActor.do" method="POST">
							<div class="form-group">
								<label for="firstName">First Name:</label> <input type="text"
									class="form-control" id="firstName" name="firstName"
									value="<c:out value='${actor.firstName}' />" minlength="1"
									maxlength="45" required>
							</div>
							<div class="form-group">
								<label for="lastName">Last Name:</label> <input type="text"
									class="form-control" id="lastName" name="lastName"
									value="<c:out value='${actor.lastName}' />" minlength="1"
									maxlength="45" required>
							</div>
							<button type="submit" class="btn btn-primary">Add</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="container">
						<div class="row">
							<div class="col">
								<p>No actor found, please go home to add or search for one.</p>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<c:if test="${not empty actor}">

			<div class="container">
				<div class="row">

					<div class="col">
						<p>Actor ID: ${actor.id}</p>
						<p>First Name: ${actor.firstName}</p>
						<p>Last Name: ${actor.lastName}</p>
						<div class="container mt-5">
							<form
								onsubmit="return window.confirm('Are you sure you want to delete this entity?');"
								action="deleteActor.do?id=<c:out value='${actor.id}' />"
								method="POST">
								<div class="form-group">
									<input type="hidden" class="form-control" id="id" name="id"
										value="<c:out value='${actor.id}' />" disabled>
								</div>
								<button type="submit" class="btn btn-danger">Delete
									this Actor</button>
							</form>
						</div>
					</div>

					<div class="col">
						<div class="container mt-5">
							<h2>Update Actor</h2>
							<form action="updateActor.do" method="POST">
								<div class="form-group">
									<label class="d-none" for="id">ID: <c:out
											value='${actor.id}' /></label> <input type="hidden"
										class="d-none form-control" id="id" name="id"
										value="<c:out value='${actor.id}' />" disabled>
								</div>


								<div class="row">

									<div class="col-3">
										<label for="firstName">First Name:</label>

									</div>

									<div class="col">
										<input type="text" class="form-control" id="firstName"
											name="firstName" value="<c:out value='${actor.firstName}' />"
											minlength="1" maxlength="45" required>

									</div>

								</div>

								<div class="row">

									<div class="col-3">

										<label for="lastName">Last Name:</label>
									</div>

									<div class="col">
										<input type="text" class="form-control" id="lastName"
											name="lastName" value="<c:out value='${actor.lastName}' />"
											minlength="1" maxlength="45" required>

									</div>

								</div>

								<br>

								<button type="button"
									onclick="alert('Update Not Completed! Too much work already!');"
									class="btn btn-primary">Update</button>

							</form>

						</div>

					</div>

				</div>
			</div>

		</c:if>

	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>

</body>

</html>