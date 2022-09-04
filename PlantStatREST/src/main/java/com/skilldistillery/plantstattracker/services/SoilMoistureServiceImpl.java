package com.skilldistillery.plantstattracker.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantstattracker.entities.SoilMoistureReading;
import com.skilldistillery.plantstattracker.repositories.SoilMoistureReadingRepository;

@Service
public class SoilMoistureServiceImpl implements SoilMoistureService {
	@Autowired
	private SoilMoistureReadingRepository soilMoistureRepo;

	// CREATE
	public SoilMoistureReading create(SoilMoistureReading soilMoistureReading) {
		soilMoistureReading.setId(null);
		return soilMoistureRepo.saveAndFlush(soilMoistureReading);
	}

	// READ
	public SoilMoistureReading findById(Long id) {
		Optional<SoilMoistureReading> soilMoistureOpt = soilMoistureRepo.findById(id);
		return soilMoistureOpt.isPresent() ? soilMoistureOpt.get() : null;
	}
	
	public List<SoilMoistureReading> findByPlant_Id(Long id) {
		return soilMoistureRepo.findByPlant_Id(id);
	}

	public List<SoilMoistureReading> findAll() {
		return soilMoistureRepo.findAll();
	}

	// UPDATE
	public SoilMoistureReading update(Long id, SoilMoistureReading soilMoistureReading) {
		Optional<SoilMoistureReading> soilMoistureOpt = soilMoistureRepo.findById(id);
		if (soilMoistureOpt.isPresent()) {
			SoilMoistureReading toUpdate = soilMoistureOpt.get();
			Field[] fields = soilMoistureReading.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("id") &&
						!field.getName().equals("dateRecorded")) {
					field.setAccessible(true);
					Object value = null;
					try {
						value = field.get(soilMoistureReading);
					} catch (IllegalAccessException iae) {
						System.out.println("Illegal Access Exception: Cannot get " + field.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (value != null) {
						try {
							field.set(toUpdate, value);
						} catch (IllegalAccessException iae) {
							System.out.println("Illegal Access Exception: Cannot set " + field.getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			return soilMoistureRepo.saveAndFlush(toUpdate);
		}
		return null;
	}
	
	// DELETE
	public boolean deleteById(Long id) {
		soilMoistureRepo.deleteById(id);
		return !soilMoistureRepo.existsById(id);
	}
}
