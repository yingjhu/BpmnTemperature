package com.diplab.temperature;

public class TemperatureReceiver {
	public Temperature getTemperature() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Temperature();
	}
}
