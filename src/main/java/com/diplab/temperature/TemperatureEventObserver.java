package com.diplab.temperature;

import java.util.Date;
import java.util.Map;


public interface TemperatureEventObserver {
	boolean isSatisfy(Map<Date, Temperature> records);
	boolean activate(Map<Date, Temperature> records);
}
