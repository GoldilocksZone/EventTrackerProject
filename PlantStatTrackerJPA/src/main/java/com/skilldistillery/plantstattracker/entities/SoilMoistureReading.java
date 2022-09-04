package com.skilldistillery.plantstattracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="soil_moisture")
public class SoilMoistureReading {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@Column(name="date_recorded")
	private LocalDateTime dateRecorded;
	private int moisture;
	@ManyToOne
	@JoinColumn(name="plant_id")
	@JsonIgnoreProperties({"soilMoistureReadings"})
	private Plant plant;
	
	public SoilMoistureReading() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateRecorded() {
		return dateRecorded;
	}

	public void setDateRecorded(LocalDateTime dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	public int getMoisture() {
		return moisture;
	}

	public void setMoisture(int moisture) {
		this.moisture = moisture;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateRecorded, id, moisture, plant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoilMoistureReading other = (SoilMoistureReading) obj;
		return Objects.equals(dateRecorded, other.dateRecorded) && Objects.equals(id, other.id)
				&& moisture == other.moisture && Objects.equals(plant, other.plant);
	}

	@Override
	public String toString() {
		return "SoilMoistureReading [id=" + id + ", dateRecorded=" + dateRecorded + ", moisture=" + moisture
				+ ", plant=" + plant + "]";
	}
}