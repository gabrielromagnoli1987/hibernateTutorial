package org.javabrains.koushik.dto;

import javax.persistence.Entity;

@Entity
public class FourWheeler extends Vehicle {
	
	private String SteeringWheel;

	public String getSteeringWheel() {
		return SteeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		SteeringWheel = steeringWheel;
	}
	
}
