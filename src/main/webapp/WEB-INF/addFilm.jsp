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

		<h1>Add Film</h1>

		<c:if test="${empty film}">

			<div class="container mt-5">

				<form action="addFilm.do" method="POST">

					<div class="form-group">
						<div class="row">
							<div class="col-3 text-right">
								<label for="title">Title:</label>
							</div>
							<div class="col">
								<input type="text" class="form-control" id="title" name="title"
									value="<c:out value='' />" minlength="1" maxlength="255"
									required>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-3">
							<label for="description">Description:</label>

						</div>
						<div class="col">
							<input type="text" class="form-control" id="description"
								name="description" value="<c:out value='' />" minlength="1"
								maxlength="65535">

						</div>
					</div>

					<div class="row">
						<div class="col-3">
							<label for="releaseYear">Release year:</label>
						</div>
						<div class="col">
							<input type="text" class="form-control" id="releaseYear" pattern="[0-9]{4}"
								name="releaseYear" value="<c:out value='' />" minlength="0"
								maxlength="4">
						</div>
					</div>

					<div class="row">
						<div class="col-3">
							<label for="languageId">Language:</label>
						</div>
						<div class="col">
							<select class="form-control" id="languageId" name="languageId"
								required>
								<option selected value="">Select an option</option>
								<option value="1">English</option>
								<option value="2">Italian</option>
								<option value="3">Japanese</option>
								<option value="4">Mandarin</option>
								<option value="5">French</option>
								<option value="6">German</option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-3">
							<label for="title">Rental duration:</label>
						</div>
						<div class="col">
							<input type="number" class="form-control" id="rentalDuration"
								name="rentalDuration" value="<c:out value='0' />" minlength="1"
								maxlength="3" min="0" max="255" required>
						</div>
					</div>


					<div class="row">
						<div class="col-3">
							<label for="rentalRate">Rental rate:</label>
						</div>
						<div class="col">

							<input type="text" class="form-control" id="rentalRate"
								name="rentalRate" placeholder=“##.##”
								value="<c:out value='0.00' />" minlength="1" maxlength="5"
								required>
						</div>
					</div>

					<div class="row">
						<div class="col-3">
							<label for="length">Length:</label>
						</div>
						<div class="col">
							<input type="text" class="form-control" id="length" pattern="[0-9]{5}"
								name="length" value="<c:out value='' />" minlength="0"
								maxlength="5">
						</div>
					</div>

					<div class="row">
						<div class="col-3">
							<label for="replacementCost">Replacement cost:</label>
						</div>
						<div class="col">
							<input type="text" class="form-control" id="replacementCost"
								name="replacementCost" value="<c:out value='0.00' />"
								minlength="1" maxlength="5" required>
						</div>
					</div>


					<div class="row">
						<div class="col-3">
							<label for="rating">Rating:</label>
						</div>
						<div class="col">
							<select class="form-control" id="rating" name="rating">
								<option value="">Select an option</option>
								<option value="G">G</option>
								<option value="PG">PG</option>
								<option value="PG13">PG13</option>
								<option value="R">R</option>
								<option value="NC17">NC17</option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-3">
							<label for="">Special features:</label>
						</div>
						<div class="col">
							<input type="text" class="form-control" id="specialFeatures"
								name="specialFeatures" value="<c:out value='' />" minlength="0"
								maxlength="45">
						</div>
					</div>

					<button type="submit" class="btn btn-primary">Add</button>

				</form>
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