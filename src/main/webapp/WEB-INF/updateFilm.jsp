<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>updateFilm</title>
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

		<h1>Film</h1>

		<c:if test="${! empty film}">

			<div class="container mt-5">
				<h2>Film Update Form</h2>
				<form action="updateFilm.do" method="POST">
				<div class="form-group">
						<label for="id">ID: ${film.id} </label> <input type="hidden"
							class="form-control" id="id" name="id"
							value="<c:out value='${film.id}' />" required>
					</div>
					<div class="form-group">
						<label for="title">Title:</label> <input type="text"
							class="form-control" id="title" name="title"
							value="<c:out value='${film.title}' />" minlength="1"
							maxlength="255" required>
					</div>
					<div class="form-group">
						<label for="description">Description:</label> <input type="text"
							class="form-control" id="description" name="description"
							value="<c:out value='${film.description}' />" minlength="1"
							maxlength="65535">
					</div>

					<div class="form-group">
						<label for="releaseYear">Release year:</label> <input
							type="number" class="form-control" id="releaseYear"
							name="releaseYear"
							value="<c:out value='${film.releaseYear}' />" minlength="0"
							maxlength="4" min="1901" max="2155">
					</div>

					<div class="form-group">
						<label for="languageId">Language:</label> <select
							class="form-control" id="languageId" name="languageId" required>
							<option value="">Select an option</option>

							<option <c:if test="${film.languageId ==1 }"> selected </c:if>
								value="1">English</option>
							<option <c:if test="${film.languageId ==2 }"> selected </c:if>
								value="2">Italian</option>
							<option <c:if test="${film.languageId ==3 }"> selected </c:if>
								value="3">Japanese</option>
							<option <c:if test="${film.languageId ==4 }"> selected </c:if>
								value="4">Mandarin</option>
							<option <c:if test="${film.languageId ==5 }"> selected </c:if>
								value="5">French</option>
							<option <c:if test="${film.languageId ==6 }"> selected </c:if>
								value="6">German</option>
						</select>
					</div>


					<div class="form-group">
						<label for="title">Rental duration:</label> <input type="number"
							class="form-control" id="rentalDuration" name="rentalDuration"
							value="<c:out value='${film.rentalDuration}' />" minlength="1" maxlength="3"
							min="0" max="255" required>
					</div>
					<div class="form-group">
						<label for="rentalRate">Rental rate:</label> <input type="text"
							class="form-control" id="rentalRate" name="rentalRate"
							placeholder=“##.##” value="<c:out value= '${film.rentalRate}' />"
							minlength="1" maxlength="5" required>
					</div>

					<div class="form-group">
						<label for="length">Length:</label> <input type="number"
							class="form-control" id="length" name="length"
							value="<c:out value='${film.length}' />" minlength="0"
							maxlength="5">
					</div>

					<div class="form-group">
						<label for="replacementCost">Replacement cost:</label> <input
							type="text" class="form-control" id="replacementCost"
							name="replacementCost"
							value="<c:out value='${film.replacementCost}'/>"
							minlength="1" maxlength="5" required>
					</div>

					<div class="form-group">
						<label for="rating">Rating:</label> <select class="form-control"
							id="rating" name="rating">
							<option value="">Select an option</option>
							<option value="G" ${film.rating == 'G' ? 'selected' : ''}>G</option>
							<option value="PG" ${film.rating == 'PG' ? 'selected' : ''}>PG</option>
							<option value="PG13" ${film.rating == 'PG13' ? 'selected' : ''}>PG13</option>
							<option value="R" ${film.rating == 'R' ? 'selected' : ''}>R</option>
							<option value="NC17" ${film.rating == 'NC17' ? 'selected' : ''}>NC17</option>
						</select>
					</div>

					<div class="form-group">
						<label for="">Special features:</label> <input type="text"
							class="form-control" id="specialFeatures" name="specialFeatures"
							value="<c:out value='${film.specialFeatures}' />"
							minlength="0" maxlength="45">
					</div>


					<button type="submit" class="btn btn-primary">Update</button>
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