<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Actors</title>
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

			<div class="alert alert-success" role="alert">
				<h1>ACTORS</h1>
			</div>

			<hr>

			<c:if test="${! empty actors}">
				<p>
					<span>${actors.size()}</span> : Actors(s) Found
				</p>
			</c:if>

			<div class="container mt-5">

				<div class="row">

					<c:forEach var="actor" items="${actors}">
						<!-- Card -->
						<div class="col-md-4">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">
										<a
											href="<c:url value='findActor.do'><c:param name='id' value='${actor.id}'/></c:url>">${actor.id}:
											${actor.firstName} ${actor.lastName} </a>
									</h5>
								</div>
							</div>
						</div>
						<!-- End Card -->
					</c:forEach>

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