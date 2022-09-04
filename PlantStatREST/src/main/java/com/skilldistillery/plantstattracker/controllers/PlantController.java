package com.skilldistillery.plantstattracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.plantstattracker.entities.Plant;
import com.skilldistillery.plantstattracker.services.PlantService;

@RestController
@RequestMapping("api/plants")
public class PlantController {
	@Autowired
	private PlantService plantService;
	private final String url = "http://localhost:8083/api/";

	// CREATE
	@PostMapping("")
	public Plant create(@RequestBody Plant plant, HttpServletResponse response) {
		Plant createdPlant = null;
		try {
			createdPlant = plantService.create(plant);
			response.setStatus(201);
			response.setHeader("Result", "Plant added to database");
			response.setHeader("Location", url + createdPlant.getId());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.setHeader("Result", "Unable to add plant to database");
		}
		return createdPlant;
	}
	
	// READ
	@GetMapping("{id}")
	public Plant findById(@PathVariable Long id, HttpServletResponse response) {
		Plant plant = null;
		try {
			plant = plantService.findById(id);
			if (plant != null) {
				response.setStatus(200);
				response.setHeader("Result", "Plant found");
			} else {
				throw new Exception("Plant not found: id " + id + " does not exist.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(404);
			response.setHeader("Result", "Plant not found");
			response.setHeader("Error", "Invalid plant id");
		}
		return plant;
	}
	
	@GetMapping("")
	public List<Plant> findAll(HttpServletResponse response) {
		List<Plant> plants = new ArrayList<>();
		try {
			plants = plantService.findAll();
			if (plants.size() > 0) {
				response.setStatus(200);
				response.setHeader("Result", "Plant list successfully returned");
			} else {
				response.setStatus(404);
				response.setHeader("Result", "No plants found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.setHeader("Result", "Unable to create plant list");
		}
		return plants;
	}
	
	// UPDATE
	@PutMapping("{id}")
	public Plant update(@PathVariable Long id, @RequestBody Plant plant, HttpServletResponse response) {
		System.out.println(plant);
		Plant updatedPlant = null;
		try {
			updatedPlant = plantService.update(id, plant);
			if (updatedPlant != null) {
				response.setStatus(200);
				response.setHeader("Result", "Plant successfully updated");
				response.setHeader("Location", url + updatedPlant.getId());
			} else {
				throw new Exception("Unable to update plant");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.setHeader("Result", "Unable to update plant");
		}
		return updatedPlant;
	}
	
	// DELETE
	@DeleteMapping("{id}")
	public boolean deleteById(@PathVariable Long id, HttpServletResponse response) {
		boolean success = false;
		try {
			success = plantService.deleteById(id);
			if (success) {
				response.setStatus(200);
				response.setHeader("Result", "Plant successfully deleted");
			} else {
				throw new Exception("Unable to delete plant with id " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.setHeader("Result", "Unable to delete plant");
		}
		return success;
	}
}
