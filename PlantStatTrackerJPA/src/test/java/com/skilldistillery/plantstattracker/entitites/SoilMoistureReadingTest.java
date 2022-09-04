package com.skilldistillery.plantstattracker.entitites;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.plantstattracker.entities.SoilMoistureReading;

class SoilMoistureReadingTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private SoilMoistureReading soilMoistureReading;

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
		soilMoistureReading = em.find(SoilMoistureReading.class, Long.valueOf(1));
	}

	@AfterEach
	void tearDown() throws Exception {
		soilMoistureReading = null;
		em.close();
	}

	@Test
	void test_soilMoistureReading_not_null() {
		assertNotNull(soilMoistureReading);
	}
	
	@Test
	void test_SoilMoistureReading_entity_mapping() {
		assertTrue(soilMoistureReading.getMoisture() == 12);
		assertTrue(soilMoistureReading.getPlant().getId() == 2);
	}

}
