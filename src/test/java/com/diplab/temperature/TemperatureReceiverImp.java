package com.diplab.temperature;

public class TemperatureReceiverImp implements TemperatureReceiver {
	public Temperature getTemperature() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Temperature(Math.random() * (45 - 30 + 1) + 30);
	}
}
