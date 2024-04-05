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

	@GetMapping(path = { "find.do" })
	public String findFilmById(@RequestParam("id") int filmId, Model model) {
		Film film = null;
		film = dao.findFilmById(filmId);

		if (film == null) {
			return "filmNotFound";
		} else {
			model.addAttribute("film", film);
			return "film";
		}

	}

}
