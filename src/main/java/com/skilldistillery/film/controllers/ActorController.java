package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping(path = { "addActor.do" })
	public String addActorGet(Model model) {
		Actor actor = null;
		model.addAttribute("add", true);
		model.addAttribute("actor", actor);
		return "actor"; // show the actor details page
	}

	@PostMapping(path = { "addActor.do" })
	public String addActorPost(@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName, Model model) {
		Actor actor = new Actor(firstName, lastName);
		actor = dao.createActor(actor);
		model.addAttribute("actor", actor);
		return "actor"; // show the actor details page
	}

	@PostMapping(path = { "deleteActor.do" })
	public String deleteActor(@RequestParam(name = "id", required = true, defaultValue = "0") int id, Model model,
			RedirectAttributes redirectAttributes) {

		System.out.println("id: " + id);

		if (id < 1) {
			return "actor"; // possible actor not found page
		}

		boolean wasDeleted = dao.deleteActor(id);

		if (wasDeleted) {
			redirectAttributes.addFlashAttribute("message", "Actor deleted successfully.");
		} else {
			redirectAttributes.addFlashAttribute("message", "Actor not deleted. ID: " + id + " not found.");
		}

		return "redirect:findActor.do"; // show the actor details page";
	}

}
