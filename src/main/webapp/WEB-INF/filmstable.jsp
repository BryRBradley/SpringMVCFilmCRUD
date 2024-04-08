<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Films Table</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<style>
table {
	border: 1px solid gray;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid gray;
	padding: 8px;
}

.found {
	background-color: lightgreen;
}
</style>
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

			<div class="alert alert-success" role="alert">
				<h1>FILMS</h1>
				<p>
					Search results using the keyword: <strong>'${keyword}'</strong>
				</p>

				<form action="findFilms.do" method="GET">

					<div class="row">
						<div class="col-3">
							<label for="name">Search</label>
						</div>
						<div class="col">
							<input type="text" class="form-control" id="name" name="name"
								value="${keyword}" placeholder="Enter a keyword">
						</div>
						<div class="col">
							<button type="submit" class="btn btn-primary">Search</button>
						</div>

						<div class="col">
							<a href="?name=${keyword}" class="btn btn-outline">Any Rating</a>
							<a href="?name=${keyword}&rating=G" class="btn btn-outline">G
								Rated</a> <a href="?name=${keyword}&rating=PG"
								class="btn btn-outline">PG Rated</a> <a
								href="?name=${keyword}&rating=PG13" class="btn btn-outline">PG13
								Rated</a> <a href="?name=${keyword}&rating=R"
								class="btn btn-outline">R Rated</a> <a
								href="?name=${keyword}&rating=NC17" class="btn btn-outline">NC17
								Rated</a>
						</div>
					</div>

				</form>


			</div>

			<hr>

			<c:if test="${! empty films}">
				<p>
					<span>${films.size()}</span> : Film(s) Found
				</p>
			</c:if>

			<c:if test="${empty films}">
				<p>No Films Found</p>
			</c:if>

			<div class="container mt-5">
				<div class="row">

					<table>

						<thead>

							<tr>

								<th>Title</th>

								<th>Description</th>

								<th>Rating</th>

								<th>Language</th>

								<th>Rental Rate</th>

								<th>Special Features</th>

								<th>Edit</th>

								<th>Delete</th>

								<th>Actors</th>

							</tr>

						</thead>

						<tbody>


							<c:forEach var="film" items="${films}">

								<c:if test="${empty rating}">
									<tr>
								</c:if>
								<c:if test="${! empty rating}">
									<c:if test="${film.rating == rating}">
										<tr class="found">
									</c:if>
									<c:if test="${film.rating != rating}">
										<tr>
									</c:if>

								</c:if>

								<td><a
									href="<c:url value='findFilm.do'><c:param name='id' value='${film.id}'/></c:url>">
										${film.id}: ${film.title} </a></td>

								<td>

									<h5 class="card-text">${film.description}</h5>

								</td>

								<td>${film.rating}</td>

								<td>${film.language}</td>

								<td>${film.rentalRate}</td>

								<td>${film.specialFeatures}</td>

								<td>

									<form action="updateFilm.do" method="GET">
										<div class="form-group">
											<input type="hidden" class="form-control" id="id" name="id"
												value="<c:out value='${film.id}' />">
										</div>
										<button type="submit" class="btn btn-warning">Edit
											Film</button>
									</form>

								</td>

								<td>

									<form action="deleteFilm.do" method="POST"
										onsubmit="return window.confirm('Are you sure you want to delete this entity?');">
										<div class="form-group">
											<input type="hidden" class="form-control" id="id" name="id"
												value="<c:out value='${film.id}' />">
										</div>
										<button type="submit" class="btn btn-danger">Delete
											this Film</button>
									</form>
								</td>

								<td>
								
								
								
								<c:if test="${empty film.actors}">
										<p>No data for Actors in film</p>

									</c:if> 
									
									
									
									<c:if test="${not empty film.actors}">
										<p>Cast of Actors</p>
										<c:forEach var="actor" items="${film.actors}">
											<h5 class="card-title">
												<a style="font-size: .8em;"
													href="<c:url value='findActor.do'><c:param name='id' value='${actor.id}'/></c:url>">${actor.id}:
													${actor.firstName} ${actor.lastName} </a><br>
											</h5>
										</c:forEach>
									</c:if>
									
									
									
									</td>
									
									

								</tr>

							</c:forEach>


						</tbody>

					</table>

				</div>
			</div>

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