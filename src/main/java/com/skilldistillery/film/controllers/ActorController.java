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
	public String findActorById(@RequestParam(name = "id", required = false, defaultValue = "0") int id, Model model,
			RedirectAttributes redirectAttributes) {

		if (id < 1) {
			redirectAttributes.addFlashAttribute("message", "Id must be valid.");
			redirectAttributes.addFlashAttribute("error", "Id must be valid.");
			return "redirect:error.do";
		}

		Actor actor = null;

		try {
			actor = dao.findActorById(id);
		} catch (Exception e) {
			actor = null;
			System.out.println("Exception: " + e.getMessage());
			redirectAttributes.addFlashAttribute("message", "Could not find actor.");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		model.addAttribute("add", false);
		model.addAttribute("actor", actor);
		return "actor";
	}

	@GetMapping(path = { "addActor.do" })
	public String addActorGet(Model model) {
		Actor actor = null;
		model.addAttribute("add", true);
		model.addAttribute("actor", actor);
		return "actor";
	}

	@PostMapping(path = { "addActor.do" })
	public String addActorPost(@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName, Model model,
			RedirectAttributes redirectAttributes) {

		Actor actor = new Actor(firstName, lastName);

		try {
			actor = dao.createActor(actor);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			actor = null;
			redirectAttributes.addFlashAttribute("message", "Error adding actor.");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:error.do";
		}

		redirectAttributes.addFlashAttribute("actor", actor);
		return "redirect:findActor.do?id=" + actor.getId();
	}

	@PostMapping(path = { "deleteActor.do" })
	public String deleteActor(@RequestParam(name = "id", required = true, defaultValue = "0") int id, Model model,
			RedirectAttributes redirectAttributes) {

		if (id < 1) {
			redirectAttributes.addFlashAttribute("message", "Actor not deleted. Id must be valid.");
			redirectAttributes.addFlashAttribute("error", "Actor not deleted. Id must be valid.");
			return "redirect:error.do";
		}

		if (id >= 1 && id <= 200) {
			redirectAttributes.addFlashAttribute("message", "Actor not deleted. This is an original actor.");
			redirectAttributes.addFlashAttribute("error", "Actor not deleted. This is an original actor.");
			return "redirect:error.do";
		}

		boolean wasDeleted = false;

		try {
			wasDeleted = dao.deleteActor(id);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Error deleting actor.");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}

		if (wasDeleted) {
			redirectAttributes.addFlashAttribute("message", "Actor deleted successfully.");
			redirectAttributes.addFlashAttribute("error", "Actor deleted successfully.");
			return "redirect:success.do";
		} else {
			redirectAttributes.addFlashAttribute("message", "Actor not deleted.");
			redirectAttributes.addFlashAttribute("error", "Actor not deleted.");
			return "redirect:error.do";
		}

	}

}
