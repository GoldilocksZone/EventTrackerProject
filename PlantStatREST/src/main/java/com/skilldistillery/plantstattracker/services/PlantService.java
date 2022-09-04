package com.skilldistillery.plantstattracker.services;

import java.util.List;

import com.skilldistillery.plantstattracker.entities.Plant;

public interface PlantService {
	boolean existsById(Long id);
	Plant create(Plant plant);
	Plant findById(Long id);
	List<Plant> findAll();
	Plant update(Long id, Plant plant);
	boolean deleteById(Long id);
}
