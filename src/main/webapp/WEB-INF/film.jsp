<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Film</title>
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

		<div class="container">

			<h1>Film</h1>

			<c:if test="${empty film}">
				<p>There is no film here, go home to add or search for one.</p>
			</c:if>

			<c:if test="${! empty message}">
				<p>${message}</p>
			</c:if>

			<c:if test="${! empty error}">
				<p>${error}</p>
			</c:if>

			<c:if test="${! empty film}">

				<div class="container">
					<div class="row">
						<div class="col">
							<p>
								<b>Film ID:</b> ${film.id}
							</p>
							<p>
								<b>Title:</b> ${film.title}
							</p>
							<p>
								<b>Description:</b> ${film.description}
							</p>
							<p>
								<b>Release Year:</b> ${film.releaseYear}
							</p>
							<p>
								<b>Language:</b> ${film.language}
							</p>
							<p>
								<b>Rental Duration:</b> ${film.rentalDuration}
							</p>
							<p>
								<b>Rental Rate:</b> ${film.rentalRate}
							</p>
							<p>
								<b>Length:</b> ${film.length}
							</p>
							<p>
								<b>Replacement Cost:</b> ${film.replacementCost}
							</p>
							<p>
								<b>Rating:</b> ${film.rating}
							</p>
							<p>
								<b>Special Features:</b> ${film.specialFeatures}
							</p>

							<div class="row">
								<div class="col">
									<form action="updateFilm.do" method="GET">
										<div class="form-group">
											<input type="hidden" class="form-control" id="id" name="id"
												value="<c:out value='${film.id}' />">
										</div>
										<button type="submit" class="btn btn-warning">Edit
											Film</button>
									</form>
								</div>

								<div class="col">
									<form action="deleteFilm.do" method="POST">
										<div class="form-group">
											<input type="hidden" class="form-control" id="id" name="id"
												value="<c:out value='${film.id}' />">
										</div>
										<button type="submit" class="btn btn-danger">Delete
											this Film</button>
									</form>
								</div>
							</div>

						</div>

						<div class="col">


							<c:if test="${empty film.actors}">
								<p>No data for Actors in film</p>

							</c:if>

							<c:if test="${not empty film.actors}">
								<p>Cast of Actors</p>
								<c:forEach var="actor" items="${film.actors}">
									<h5 class="card-title">
										<a
											href="<c:url value='findActor.do'><c:param name='id' value='${actor.id}'/></c:url>">${actor.id}:
											${actor.firstName} ${actor.lastName} </a>
									</h5>
								</c:forEach>
							</c:if>
						</div>

					</div>

				</div>


			</c:if>

		</div>

	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>

</body>

</html>