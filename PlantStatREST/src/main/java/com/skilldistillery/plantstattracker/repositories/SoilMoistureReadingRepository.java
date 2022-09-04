package com.skilldistillery.plantstattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantstattracker.entities.SoilMoistureReading;

public interface SoilMoistureReadingRepository extends JpaRepository<SoilMoistureReading, Long> {
	List<SoilMoistureReading> findByPlant_Id(Long id);
}
