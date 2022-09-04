package com.skilldistillery.plantstattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantstattracker.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {

}
