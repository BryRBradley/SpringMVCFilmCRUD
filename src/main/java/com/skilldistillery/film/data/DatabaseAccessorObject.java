package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		String USER = "student";
		String PASS = "student";

		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id" + "WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
//				if (releaseYear == 0) {
//					releaseYear = null;
//				}
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				Integer length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String language = rs.getString("language");
				Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rate, length,
						replacementCost, rating, specialFeatures, language);
				film.setActors(findActorsByFilmId(filmId));
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return films;
	}

	@Override
	public List<Film> findFilmsByKeyWord(String keyWord) {
		String USER = "student";
		String PASS = "student";
		Film film = null;
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id,"
					+ "film.rental_duration, film.rental_rate, film.length, film.replacement_cost, "
					+ "film.rating, film.special_features, language.name "
					+ "FROM film JOIN language ON film.language_id = language.id "
					+ "WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				if (releaseYear == 0) {
					releaseYear = null;
				}
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				Integer length = rs.getInt("length");
				if (length == 0) {
					length = null;
				}
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String language = rs.getString("name");
				film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rate, length,
						replacementCost, rating, specialFeatures, language);

				film.setActors(findActorsByFilmId(filmId));
				films.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return films;
	}

	@Override
	public Film findFilmById(int filmId) {
		String USER = "student";
		String PASS = "student";
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			film = null;
			String sql = "SELECT film.*, language.name FROM film JOIN language on film.language_id = language.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("Id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				if (film.getReleaseYear() == 0) {
					film.setReleaseYear(null);
				}
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				if (film.getLength() == 0) {
					film.setLength(null);
				}
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setLanguage(filmResult.getString("name"));
				List<Actor> actorList = findActorsByFilmId(filmId);
				film.setActors(actorList);
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return (film);

	}

	@Override
	public Actor findActorById(int actorId) {
		String USER = "student";
		String PASS = "student";
		Actor actor = null;

		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			actor = null;

			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor();

				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

			}

			actorResult.close();
			stmt.close();
			conn.close();
			return actor;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		String USER = "student";
		String PASS = "student";
		Actor actor = null;

		List<Actor> actorsList = new ArrayList<>();

		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT actor.* FROM actor Join film_actor on actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {

				actor = new Actor();

				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

				actorsList.add(actor);
			}

			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return actorsList;
	}

	public Actor createActor(Actor actor) {

		String USER = "student";
		String PASS = "student";

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);
					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}

			conn.commit(); // COMMIT TRANSACTION

		} catch (SQLException sqle) {

			sqle.printStackTrace();

			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}

			throw new RuntimeException("Error inserting actor : " + sqle.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}

		return actor;
	}

	@Override
	public Film createFilm(Film film) {

		String USER = "student";
		String PASS = "student";

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO film (title, description,release_year,language_id,rental_duration,rental_rate,length,replacement_cost,rating,special_features) "
					+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());

			// stmt.setInt(3, film.getReleaseYear() != null ? film.getReleaseYear() : null);

			if (film.getReleaseYear() != null) {
				// If getReleaseYear is not null, set the integer value
				stmt.setInt(3, film.getReleaseYear());
			} else {
				// If getReleaseYear is null, set it as NULL in the database
				stmt.setNull(3, java.sql.Types.INTEGER);
			}

			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());

			// stmt.setInt(7, film.getLength());

			if (film.getLength() != null) {
				// If getLength is not null, set the integer value
				stmt.setInt(7, film.getLength());
			} else {
				// If getLength is null, set it as NULL in the database
				stmt.setNull(7, java.sql.Types.INTEGER);
			}

			stmt.setDouble(8, film.getReplacementCost());

			// stmt.setString(9, film.getRating());

			if (film.getRating() != null && !film.getRating().equals("")) {
				// If getRating is not null, set the string value
				stmt.setString(9, film.getRating());
			} else {
				// If getRating is null, set it as NULL in the database
				stmt.setNull(9, java.sql.Types.VARCHAR);
			}

			stmt.setString(10, film.getSpecialFeatures());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {

				ResultSet keys = stmt.getGeneratedKeys();

				if (keys.next()) { // should and will be only one
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
				}

				conn.commit(); // COMMIT TRANSACTION

			} else {

				film = null;

			}

		} catch (SQLException sqle) {

			film = null;

			sqle.printStackTrace();

			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}

			System.out.println("-----------------------------------");
			System.out.println(sqle.getMessage());

			throw new RuntimeException("Error inserting film: " + sqle.getMessage());

		} catch (Exception e) {

			System.out.println("-----------------------------------");
			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new RuntimeException(e.getMessage());

		}

		System.out.println(film.getId());
		System.out.println(film);

		return film;
	}

	@Override
	public List<Actor> findAllActors() {

		String USER = "student";
		String PASS = "student";

		Actor actor = null;

		List<Actor> actors = new ArrayList<>();

		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM actor";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet actorResult = stmt.executeQuery();

			while (actorResult.next()) {

				actor = new Actor();

				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

				actors.add(actor);

			}

			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}

		return actors;
	}

	@Override
	public List<Film> findAllFilms() {

		String USER = "student";
		String PASS = "student";

		List<Film> films = new ArrayList<>();

		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.*, language.name as language FROM film JOIN language ON film.language_id = language.id order by id asc";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				Integer length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String language = rs.getString("language");

				Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rate, length,
						replacementCost, rating, specialFeatures, language);

				films.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}

		return films;
	}

	@Override
	public boolean deleteActor(int actorId) {

		String USER = "student";
		String PASS = "student";

		boolean wasDeleted = false;

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "DELETE FROM actor WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, actorId);

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				wasDeleted = true;
			} else {
				wasDeleted = false;
			}

			conn.commit(); // COMMIT TRANSACTION

		} catch (SQLException sqle) {

			sqle.printStackTrace();

			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}

			throw new RuntimeException("Error deleting actor : " + sqle.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}

		return wasDeleted;
	}

	@Override
	public boolean deleteFilm(int filmId) {

		String USER = "student";
		String PASS = "student";

		boolean wasDeleted = false;

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "DELETE FROM film WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, filmId);

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				wasDeleted = true;
			} else {
				wasDeleted = false;
			}

			conn.commit(); // COMMIT TRANSACTION

		} catch (SQLException sqle) {

			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error deleting film : " + sqle.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}

		return wasDeleted;
	}

	@Override
	public Film updateFilmById(Film film) {

		String USER = "student";
		String PASS = "student";

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?,rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? WHERE id = ?";
			;

			// "UPDATE film SET (title,
			// description,release_year,language_id,rental_duration,rental_rate,length,replacement_cost,rating,special_features)
			// "
			// + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());

			// stmt.setInt(3, film.getReleaseYear() != null ? film.getReleaseYear() : null);

			if (film.getReleaseYear() != null) {
				// If getReleaseYear is not null, set the integer value
				stmt.setInt(3, film.getReleaseYear());
			} else {
				// If getReleaseYear is null, set it as NULL in the database
				stmt.setNull(3, java.sql.Types.INTEGER);
			}
			System.out.println(film.getLanguageId());

			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());

			// stmt.setInt(7, film.getLength());

			if (film.getLength() != null) {
				// If getLength is not null, set the integer value
				stmt.setInt(7, film.getLength());
			} else {
				// If getLength is null, set it as NULL in the database
				stmt.setNull(7, java.sql.Types.INTEGER);
			}

			stmt.setDouble(8, film.getReplacementCost());

			// stmt.setString(9, film.getRating());

			if (film.getRating() != null && !film.getRating().equals("")) {
				// If getRating is not null, set the string value
				stmt.setString(9, film.getRating());
			} else {
				// If getRating is null, set it as NULL in the database
				stmt.setNull(9, java.sql.Types.VARCHAR);
			}

			stmt.setString(10, film.getSpecialFeatures());

			stmt.setInt(11, film.getId());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				conn.commit(); // COMMIT TRANSACTION
				return film;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error in updating film : " + sqle.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		System.out.println("Updated film" + film);
		return film;

	}
}
