package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.film.data.DatabaseAccessor;
import com.skilldistillery.film.entities.Actor;

@Controller
public class ActorController { // Sheldon

	@Autowired
	private DatabaseAccessor dao;

	@GetMapping(path = { "findActor.do" })
	public String findActorById(@RequestParam(name = "id", required = false, defaultValue = "0") int id, Model model) {

		if (id < 1) {
			return "actor"; // possible film not found page
		}

		Actor actor = null;
		actor = dao.findActorById(id);
		model.addAttribute("actor", actor);

		if (actor == null) {
			return "actor"; // possible actor not found page
		} else {
			return "actor"; // show the actor details page
		}

	}

}
