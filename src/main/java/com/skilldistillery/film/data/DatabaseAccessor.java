package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface DatabaseAccessor {

	// Bryan
	public Film findFilmById(int filmId);

	// Sheldon
	public Actor findActorById(int actorId);

	// Sheldon
	public List<Actor> findAllActors();

	// Sheldon
	public List<Actor> findActorsByFilmId(int filmId);

	// Bryan
	public List<Film> findFilmsByActorId(int actorId);

	// Bryan
	public List<Film> findFilmsByKeyWord(String keyWord);

	// Bryan
	public List<Film> findAllFilms();

	// Bryan
	public Film createFilm(String newFilmTitle);

	// Sheldon
	public Actor createActor(Actor actor);

}
