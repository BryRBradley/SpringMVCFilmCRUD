package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private DatabaseAccessor dao;

	@GetMapping(path = { "/", "index", "index.do" })
	public ModelAndView home(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@GetMapping(path = { "about", "about.do" })
	public String about() {
		return "about";
	}

	@GetMapping(path = { "films", "films.do" })
	public String films() {
		return "films";
	}

	@GetMapping(path = { "actors", "actors.do" })
	public String actors() {
		return "actors";
	}

	@GetMapping(path = { "find.do" })
	public String findFilmById(@RequestParam(name = "id", required = false, defaultValue = "0") int id, Model model) {

		if (id < 1) {
			return "film"; // possible film not found page
		}

		Film film = null;
		film = dao.findFilmById(id);
		model.addAttribute("film", film);

		if (film == null) {
			return "film"; // possible film not found page
		} else {
			return "film"; // show the film details page
		}

	}

}
