package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.DatabaseAccessor;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class SiteController { // Sheldon

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

	@GetMapping(path = { "actors", "actors.do" })
	public String actors(Model model) {
		List<Actor> actors = new ArrayList<>();
		actors = dao.findAllActors();
		model.addAttribute("actors", actors);
		return "actors";
	}

	@GetMapping(path = { "films", "films.do" })
	public String films(Model model) {
		List<Film> films = new ArrayList<>();
		films = dao.findAllFilms();
		model.addAttribute("films", films);
		return "films";
	}

}
