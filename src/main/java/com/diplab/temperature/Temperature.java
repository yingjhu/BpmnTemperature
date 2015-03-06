package com.diplab.temperature;

public class Temperature {

	private double temp;

	public double getTemp() {
		return temp;
	}

	public Temperature() {
		temp = Math.random() * (45 - 30 + 1) + 30;
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
