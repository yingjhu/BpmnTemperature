package com.diplab.temperature;

import java.util.Date;
import java.util.Map;

public abstract class TemperatureEventListener implements IsSatisfy{

	private IsSatisfy isSatisfy;

	public TemperatureEventListener(IsSatisfy isSatisfy) {
		this.isSatisfy = isSatisfy;
	}
	@Override
	public boolean isSatisfy(Map<Date, Temperature> records) {
		return isSatisfy.isSatisfy(records);
	}
	public abstract void activate(Map<Date, Temperature> records);

}
