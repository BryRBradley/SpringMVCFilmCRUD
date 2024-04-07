package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

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
	public String findFilmById(@RequestParam(name = "id", required = true, defaultValue = "0") int id, Model model,
			RedirectAttributes redirectAttributes) {

		if (id < 1) {
			redirectAttributes.addFlashAttribute("message", "Id must be valid.");
			redirectAttributes.addFlashAttribute("error", "Id must be valid.");
			return "redirect:error.do";
		}

		Film film = null;

		try {
			film = dao.findFilmById(id);
		} catch (Exception e) {
			film = null;
			System.out.println("Exception: " + e.getMessage());
			redirectAttributes.addFlashAttribute("message", "Could not find film.");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		model.addAttribute("film", film);
		return "film";
	}

	@PostMapping(path = { "findFilms.do" })
	public String findFilmByName(@RequestParam(name = "name", required = true, defaultValue = "") String name,
			Model model, RedirectAttributes redirectAttributes) {

		List<Film> films = new ArrayList<>();

		try {
			films = dao.findFilmsByKeyWord(name);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			films = null;
			redirectAttributes.addFlashAttribute("message", "Error finding film(s).");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		model.addAttribute("films", films);
		return "films";
	}

	@GetMapping(path = { "addFilm.do" })
	public String addFilmGet(Model model) {
		return "addFilm";
	}

	@PostMapping(path = { "addFilm.do" })
	public String addFilmPost(Film film, Model model, RedirectAttributes redirectAttributes) {

		System.out.println("POST addFilm POST");
		System.out.println(film);

		try {
			film = dao.createFilm(film);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("message", "Error adding film.");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		if (film == null) {
			redirectAttributes.addFlashAttribute("message", "Error adding film.");
			redirectAttributes.addFlashAttribute("error", "Error adding film.");
			return "redirect:error.do";
		}

		redirectAttributes.addFlashAttribute("film", film);
		return "redirect:findFilm.do?id=" + film.getId();
	}

	@PostMapping(path = { "deleteFilm.do" })
	public String deleteFilm(@RequestParam(name = "id", required = true, defaultValue = "0") int id, Model model,
			RedirectAttributes redirectAttributes) {

		System.out.println("Id: " + id);

		if (id < 1) {
			redirectAttributes.addFlashAttribute("message", "Id must be valid.");
			redirectAttributes.addFlashAttribute("error", "Id must be valid.");
			return "redirect:error.do";
		}

		if (id <= 1000) {
			redirectAttributes.addFlashAttribute("message", "Can't delete an original film.");
			redirectAttributes.addFlashAttribute("error", "Can't delete an original film.");
			return "redirect:error.do";
		}

		boolean wasDeleted = false;

		try {
			wasDeleted = dao.deleteFilm(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("message", "Error deleting film.");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		if (wasDeleted) {
			redirectAttributes.addFlashAttribute("message", "Film deleted successfully.");
			redirectAttributes.addFlashAttribute("error", "Film deleted successfully.");
			return "redirect:success.do";
		} else {
			redirectAttributes.addFlashAttribute("message", "Film not deleted.");
			redirectAttributes.addFlashAttribute("error", "Film not deleted.");
			return "redirect:error.do";
		}

	}

	@PostMapping(path = { "updateFilm.do" })
	public String updateFilmById(Film film, Model model, RedirectAttributes redirectAttributes) {

		System.out.println("Id: " + film.getId());

		if (film.getId() < 1) {
			redirectAttributes.addFlashAttribute("message", "Id must be valid.");
			return "redirect:error.do";
		}

		try {
			film = dao.updateFilmById(film);
			film = dao.findFilmById(film.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			redirectAttributes.addFlashAttribute("message", "Failed to update film");
			film = null;
			return "redirect:error.do";
		}

		if (film == null) {
			model.addAttribute("error", "Error updating film. ");
			film = null;
			return "redirect:error.do";
		} else {
			model.addAttribute("film", film);
			return "film"; // show the film details page
		}

	}

	@GetMapping(path = { "updateFilm.do" })
	public String updateFilmById(@RequestParam(name = "id", required = false, defaultValue = "0") int id, Model model,
			RedirectAttributes redirectAttributes) {

		System.out.println("Id: " + id);

		if (id < 1) {
			redirectAttributes.addFlashAttribute("message", "Id must be valid.");
			return "redirect:error.do";
		}

		Film film = null;

		try {
			film = dao.findFilmById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Error updating film " + e.getMessage());
			film = null;
			return "redirect:error.do";
		}

		if (film == null) {
			model.addAttribute("error", "Error updating film. ");
			film = null;
			return "redirect:error.do";
		} else {
			model.addAttribute("film", film);
			return "updateFilm"; // show the film details page
		}

	}

}
