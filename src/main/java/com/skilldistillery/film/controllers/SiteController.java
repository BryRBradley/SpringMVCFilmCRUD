package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping(path = { "error", "error.do" })
	public String error(Model model, RedirectAttributes redirectAttributes) {
		// Using reflection to get flash attributes from the model
		String message = (String) model.asMap().get("message");
		if (message != null) {
			System.out.println("message: " + message);
		}
		String error = (String) model.asMap().get("error");
		if (error != null) {
			System.out.println("error: " + error);
		}
		return "error";
	}

	@GetMapping(path = { "success", "success.do" })
	public String success(Model model, RedirectAttributes redirectAttributes) {
		// Using reflection to get flash attributes from the model
		String message = (String) model.asMap().get("message");
		if (message != null) {
			System.out.println("message: " + message);
		}
		String error = (String) model.asMap().get("error");
		if (error != null) {
			System.out.println("error: " + error);
		}
		return "success";
	}

	@GetMapping(path = { "about", "about.do" })
	public String about() {
		return "about";
	}

	@GetMapping(path = { "actors", "actors.do" })
	public String actors(Model model, RedirectAttributes redirectAttributes) {
		List<Actor> actors = new ArrayList<>();

		try {
			actors = dao.findAllActors();
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Error finding actor(s).");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		model.addAttribute("actors", actors);
		return "actors";
	}

	@GetMapping(path = { "films", "films.do" })
	public String films(Model model) {
		List<Film> films = new ArrayList<>();

		try {
			films = dao.findAllFilms();
		} catch (Exception e) {
			model.addAttribute("message", "Error finding film(s).");
			model.addAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		model.addAttribute("films", films);
		return "films";
	}

}
