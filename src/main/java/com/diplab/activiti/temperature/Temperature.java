package com.diplab.activiti.temperature;

public class Temperature {

	private double temp;

	public double getTemperature() {
		return temp;
	}

	public Temperature(double temp) {
		super();
		this.temp = temp;
	}

	@Override
	public String toString() {
		return String.format("T=%d", temp);
	}
}
