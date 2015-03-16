package com.diplab.activiti.temperature;

public class Temperature {

	private double temperature;

	public Temperature(double temperature) {
		super();
		this.temperature = temperature;
	}
	
	public Temperature(){}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return String.format("T=%d", temperature);
	}
}
