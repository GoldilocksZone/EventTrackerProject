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

import com.skilldistillery.plantstattracker.entities.SoilMoistureReading;
import com.skilldistillery.plantstattracker.services.PlantService;
import com.skilldistillery.plantstattracker.services.SoilMoistureService;

@RestController
@RequestMapping("api/readings/moisture")
public class SoilMoistureController {
	@Autowired
	private SoilMoistureService soilMoistureService;
	@Autowired
	private PlantService plantService;
	private String url = "http://localhost:8083/api/readings/moisture/";

	// CREATE
	@PostMapping("")
	public SoilMoistureReading create(@RequestBody SoilMoistureReading soilMoistureReading,
			HttpServletResponse response) {
		SoilMoistureReading created = null;
		if (soilMoistureReading.getPlant().getId() != null &&
				plantService.existsById(soilMoistureReading.getPlant().getId())) {
			try {
				created = soilMoistureService.create(soilMoistureReading);
				response.setStatus(201);
				response.setHeader("Result", "Soil moisture reading added to plant id:" + created.getPlant().getId());
				response.setHeader("Location", url + created.getId());
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(400);
				response.setHeader("Result", "Unable to add soil moisture reading");
			}
		} else {
			response.setStatus(400);
			response.setHeader("Result", "Unable to add soil moisture reading");
			response.setHeader("Error", "Invalid plant id");
		}
		return created;
	}

	// READ
	@GetMapping("{id}")
	public List<SoilMoistureReading> findByPlant_Id(@PathVariable Long plantId, HttpServletResponse response) {
		List<SoilMoistureReading> soilMoistureReadings = new ArrayList<>();
		try {
			soilMoistureReadings = soilMoistureService.findByPlant_Id(plantId);
			if (soilMoistureReadings.size() > 0) {
				response.setStatus(200);
				response.setHeader("Result", "Soil moisture readings found for plant id " + plantId);
			} else {
				throw new Exception("Unable to retrieve soil moisture list for plant id " + plantId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(404);
			response.setHeader("Result", "Unable to retrieve soil moisture list for plant id " + plantId);
		}
		return soilMoistureReadings;
	}
//	public SoilMoistureReading findById(
//			@PathVariable Long id,
//			HttpServletResponse response) {
//		SoilMoistureReading soilMoistureReading = null;
//		try {
//			soilMoistureReading = soilMoistureService.findById(id);
//			if (soilMoistureReading != null) {
//				response.setStatus(200);
//				response.setHeader("Result", "Soil moisture reading found");
//			} else {
//				throw new Exception("Unable to locate soil moisture reading");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setStatus(404);
//			response.setHeader("Result", "Unable to locate soil moisture reading");
//		}
//		return soilMoistureReading;
//	}

	@GetMapping("")
	public List<SoilMoistureReading> findAll(HttpServletResponse response) {
		List<SoilMoistureReading> soilMoistureReadings = new ArrayList<>();
		try {
			soilMoistureReadings = soilMoistureService.findAll();
			if (soilMoistureReadings.size() > 0) {
				response.setStatus(200);
				response.setHeader("Result", "Soil moisture reading list successfully returned");
			} else {
				response.setStatus(404);
				response.setHeader("Result", "No soil moisture readings found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.setHeader("Result", "Unable to create soil moisture reading list");
		}
		return soilMoistureReadings;
	}

	// UPDATE
	@PutMapping("{id}")
	public SoilMoistureReading update(@PathVariable Long id, @RequestBody SoilMoistureReading soilMoistureReading,
			HttpServletResponse response) {
		SoilMoistureReading updated = null;
		try {
			updated = soilMoistureService.update(id, soilMoistureReading);
			response.setStatus(200);
			response.setHeader("Result", "Soil moisture reading successfully updated");
			response.setHeader("Location", url + updated.getId());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.setHeader("Result", "Unable to update soil moisture reading id: " + id);
		}
		return updated;
	}

	// DELETE
	@DeleteMapping("{id}")
	public boolean deleteById(@PathVariable Long id, HttpServletResponse response) {
		boolean success = false;
		try {
			success = soilMoistureService.deleteById(id);
			if (success) {
				response.setStatus(200);
				response.setHeader("Result", "Soil moisture reading id " + id + " successfully deleted");
			} else {
				throw new Exception("Unable to delete soil mositure reading id" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.setHeader("Result", "Unable to delete soil moisture reading id " + id);
		}
		return success;
	}

}
