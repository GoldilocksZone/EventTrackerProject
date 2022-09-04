package com.skilldistillery.plantstattracker.entitites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.plantstattracker.entities.Plant;

class PlantTester {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Plant plant;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("PlantStatTrackerJPA");
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		plant = em.find(Plant.class, Long.valueOf(1));
	}
	
	@AfterEach
	void setup() throws Exception {
		em.close();
		plant = null;
	}
	
	@Test
	void test_plant_not_null() {
		assertNotNull(plant);
	}
	
	@Test
	void test_Plant_entity_mappings() {
		assertEquals(plant.getScientificName(), "Ficus Lyrata");
		assertEquals(plant.getNickname(), "Figgy");
	}
}
