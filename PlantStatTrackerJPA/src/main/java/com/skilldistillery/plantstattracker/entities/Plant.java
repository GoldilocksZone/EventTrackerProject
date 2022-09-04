package com.skilldistillery.plantstattracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Plant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="scientific_name")
	private String scientificName;
	private String nickname;
	@OneToMany(mappedBy="plant", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonIgnoreProperties({"plant"})
	private List<SoilMoistureReading> soilMoistureReadings;
	
	public Plant() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<SoilMoistureReading> getSoilMoistureReadings() {
		return soilMoistureReadings;
	}

	public void setSoilMoistureReadings(List<SoilMoistureReading> soilMoistureReadings) {
		this.soilMoistureReadings = soilMoistureReadings;
	}
	
	public void add(SoilMoistureReading soilMoistureReading) {
		if (soilMoistureReadings == null) {
			soilMoistureReadings = new ArrayList<SoilMoistureReading>();
		}
		if (!soilMoistureReadings.contains(soilMoistureReading)) {
			soilMoistureReadings.add(soilMoistureReading);
			if(soilMoistureReading.getPlant() != null) {
				soilMoistureReading.getPlant().getSoilMoistureReadings().remove(soilMoistureReading);
			}
			soilMoistureReading.setPlant(this);
		}
	}
	
	public void remove(SoilMoistureReading soilMoistureReading) {
		soilMoistureReading.setPlant(null);
		if (soilMoistureReading != null) {
			soilMoistureReadings.remove(soilMoistureReading);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nickname, scientificName, soilMoistureReadings);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		return Objects.equals(id, other.id) && Objects.equals(nickname, other.nickname)
				&& Objects.equals(scientificName, other.scientificName)
				&& Objects.equals(soilMoistureReadings, other.soilMoistureReadings);
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", scientificName=" + scientificName + ", nickname=" + nickname
				+ ", soilMoistureReadings=" + soilMoistureReadings + "]";
	}
}
