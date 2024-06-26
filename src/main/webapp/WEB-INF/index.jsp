<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JSP MVC Film Query</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<style>
.btn-add {
	color: green;
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
				<h1>MVC Film Query</h1>
				<p>Welcome to the MVC Film Query Project</p>
			</div>

			<hr>

			<div class="container">
				<div class="row">
					<div class="col">
						<h1>Films</h1>

						<form action="findFilm.do" method="GET">
							<label for="id" class="form-label">Film by Id: </label> <input
								type="number" name="id" min="1" step="1" placeholder="" /> <input
								class="btn btn-success" type="submit" value="Search By Id" />
						</form>

						<hr>

						<form action="findFilms.do" method="POST">
							<label for="name" class="form-label">Film by Keyword: </label> <input
								type="text" name="name" minlength="0" maxlength="255"
								placeholder="" /> <input class="btn btn-success" type="submit"
								value="Search By Keyword" />
						</form>


						<hr>

						<form action="addFilm.do" method="GET">
							<input type="submit" class="btn btn-outline btn-add" value="+ Add Film"
								title="Add Film" />
						</form>

					</div>

					<div class="col">

						<h1>Actors</h1>

						<form action="findActor.do" method="GET">
							<label for="id" class="form-label">Actor by Id: </label> <input
								type="number" name="id" min="1" step="1" placeholder="" /> <input
								type="submit" class="btn btn-success" value="Search By Id" />
						</form>

						<hr>

						<form action="addActor.do" method="GET">
							<input type="submit" class="btn btn-outline btn-add"
								value="+ Add Actor" title="Add Actor" />
						</form>

					</div>
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

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>


	<hr>

</body>

</html>