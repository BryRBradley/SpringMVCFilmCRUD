package com.skilldistillery.film.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Actor { // Sheldon
	private int id;
	private String firstName;
	private String lastName;

	List<Film> films = new ArrayList<>();

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public int getId() {
		return id;
	}

	public Actor() {
	}

	public Actor(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Actor id: " + id + ", First Name: " + firstName + ", lastName: " + lastName;
	}

}
