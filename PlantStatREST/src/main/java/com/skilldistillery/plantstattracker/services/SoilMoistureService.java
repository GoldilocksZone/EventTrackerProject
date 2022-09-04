package com.skilldistillery.plantstattracker.services;

import java.util.List;

import com.skilldistillery.plantstattracker.entities.SoilMoistureReading;

public interface SoilMoistureService {
	SoilMoistureReading create(SoilMoistureReading soilMoistureReading);
	SoilMoistureReading findById(Long id);
	List<SoilMoistureReading> findByPlant_Id(Long id);
	List<SoilMoistureReading> findAll();
	SoilMoistureReading update(Long id, SoilMoistureReading soilMoistureReading);
	boolean deleteById(Long id);
}
