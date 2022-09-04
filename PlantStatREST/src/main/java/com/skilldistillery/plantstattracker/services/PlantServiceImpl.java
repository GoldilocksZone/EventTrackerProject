package com.skilldistillery.plantstattracker.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantstattracker.repositories.PlantRepository;
import com.skilldistillery.plantstattracker.entities.Plant;

@Service
public class PlantServiceImpl implements PlantService {
	@Autowired
	private PlantRepository plantRepo;
	
	// EXISTS
	@Override
	public boolean existsById(Long id) {
		return plantRepo.existsById(id);
	}

	// CREATE
	@Override
	public Plant create(Plant plant) {
		plant.setId(null);
		return plantRepo.saveAndFlush(plant);
	}

	// READ
	@Override
	public Plant findById(Long id) {
		Optional<Plant> plantOpt = plantRepo.findById(id);
		return plantOpt.isPresent() ? plantOpt.get() : null;
	}

	@Override
	public List<Plant> findAll() {
		return plantRepo.findAll();
	}

	// UPDATE
	@Override
	public Plant update(Long id, Plant plant) {
		Optional<Plant> toUpdateOpt = plantRepo.findById(id);
		if (toUpdateOpt.isPresent()) {
			Plant toUpdate = toUpdateOpt.get();
			Field[] fields = plant.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("id")) {
					field.setAccessible(true);
					Object value = null;
					try {
						value = field.get(plant);
					} catch (IllegalAccessException iae) {
						System.out.println("Illegal Access Exception: Unable to get " + field.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (value != null) {
						try {
							field.set(toUpdate, value);
						} catch (IllegalAccessException iae) {
							System.out.println("Illegal Access Exception: Unable to set " + field.getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			return plantRepo.saveAndFlush(toUpdate);
		}
		return null;
	}

	// DELETE
	@Override
	public boolean deleteById(Long id) {
		plantRepo.deleteById(id);
		return !plantRepo.existsById(id);
	}

}
