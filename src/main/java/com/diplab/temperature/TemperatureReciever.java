package com.diplab.temperature;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class TemperatureReciever {

	public Map<Date, Temperature> getTemperature() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Map<Date,Temperature> map = new HashMap<Date,Temperature>();
		
		Date date = null;
		Temperature temp = null;
		map.put(date,temp);
		
		return map;
		

	}

}
