package com.diplab.temperature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemperatureEventScheduler implements Runnable {
	private Map<Date, Temperature> records = Collections
			.synchronizedMap(new HashMap<Date, Temperature>());
	List<TemperatureEventObserver> observers = new ArrayList<TemperatureEventObserver>();

	public void addTemperatureEventObserver(
			TemperatureEventObserver eventObserver) {
		observers.add(eventObserver);
	}

	@Override
	public void run() {

		// check observer and activate
		// thread safe?
		new Thread() {
			public void run() {
				while (true) {
					for (TemperatureEventObserver temperatureEventObserver : observers) {
						if (temperatureEventObserver.isSatisfy(records)) {
							temperatureEventObserver.activate(records);
							observers.remove(temperatureEventObserver);
						}
					}
				}
			};
		}.start();

		// get temperature
		new Thread() {

			public void run() {
				while (true) {
					TemperatureReciever temperatureReciever = new TemperatureReciever();
					records.putAll(temperatureReciever.getTemperature());
				}
			};
		}.start();
	}

}
