package com.diplab.temperature;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TemperatureReciever {

	public Map<Date, Temperature> getTemperature() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Map<Date, Temperature> map = new HashMap<Date, Temperature>();

		Date date = new Date();
		Temperature temp = new Temperature();
		map.put(date, temp);

		return map;

	}

}
