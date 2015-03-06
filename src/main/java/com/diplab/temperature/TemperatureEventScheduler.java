package com.diplab.temperature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TemperatureEventScheduler extends Thread {
	private Map<Date, Temperature> records = Collections
			.synchronizedMap(new TreeMap<Date, Temperature>(Collections
					.reverseOrder()));

	private List<TemperatureEventListener> observers = new ArrayList<TemperatureEventListener>();

	private static TemperatureEventScheduler temperatureEventScheduler = new TemperatureEventScheduler();

	public static TemperatureEventScheduler getTemperatureEventScheduler() {
		return temperatureEventScheduler;
	}

	private TemperatureEventScheduler() {
	}

	public void addTemperatureEventObserver(
			TemperatureEventListener eventObserver) {
		observers.add(eventObserver);
	}

	@Override
	public void run() {

		// check observer and activate
		// thread safe?
		new Thread() {
			public void run() {
				while (true) {
					for (TemperatureEventListener temperatureEventObserver : observers) {
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
