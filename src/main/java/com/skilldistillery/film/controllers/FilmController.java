package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController { // Bryan

	@Autowired
	private DatabaseAccessor dao;

	@GetMapping(path = { "findFilm.do" })
	public String findFilmById(@RequestParam(name = "id", required = false, defaultValue = "0") int id, Model model) {

		if (id < 1) {
			return "film"; // possible film not found page
		}

		Film film = null;
		film = dao.findFilmById(id);
		model.addAttribute("film", film);
		System.out.println(film);
		if (film == null) {
			return "film"; // possible film not found page
		} else {
			return "film"; // show the film details page
		}

	}

	@GetMapping(path = { "addFilm.do" })
	public String addFilmGet(Model model) {
		return "addFilm";
	}

	@PostMapping(path = { "addFilm.do" })
	public String addFilmPost(Film film, Model model) {
		System.out.println(film);
		try {
			film = dao.createFilm(film);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "error adding film " + e.getMessage());
			film = null;
		}
		model.addAttribute("film", film);
		System.out.println(film);
		return "film";
	}

	@PostMapping(path = { "deleteFilm.do" })
	public String deleteFilm(@RequestParam(name = "id", required = true, defaultValue = "0") int id, Model model,
			RedirectAttributes redirectAttributes) {

		System.out.println("id: " + id);

		if (id < 1) {
			return "film";
		}
		if (id <= 1000) {
			model.addAttribute("error", "Can't delete original films");
			return "film";
		}

		boolean wasDeleted = dao.deleteFilm(id);

		if (wasDeleted) {
			redirectAttributes.addFlashAttribute("message", "Film deleted successfully.");
		} else {
			redirectAttributes.addFlashAttribute("message", "Film not deleted. ID: " + id + " not found.");
		}

		return "redirect:findFilm.do"; // show the actor details page";
	}

	@GetMapping(path = { "updateFilm.do" })
	public String updateFilmById(@RequestParam(name = "id", required = false, defaultValue = "0") int id, Model model) {

		if (id < 1) {
			return "film"; // possible film not found page
		}
		if (id <= 1000) {
			model.addAttribute("error", "Can't update original films");
			return "film";
		}
		Film film = null;
		film = dao.findFilmById(id);
		model.addAttribute("film", film);
		System.out.println(film);
		if (film == null) {
			return "film"; // possible film not found page
		} else {
			return "updateFilm"; // show the film details page
		}

	}

}
