package com.diplab.temperature;

import java.util.Date;
import java.util.Map;

public abstract class TemperatureEventListener implements IsSatisfy {

	private IsSatisfy isSatisfy;

	public TemperatureEventListener(IsSatisfy isSatisfy) {
		super();
		this.isSatisfy = isSatisfy;
	}

	@Override
	public boolean isSatisfy(Map<Date, Temperature> records) {
		if (records.size() == 0)
			return false;
		return isSatisfy.isSatisfy(records);
	}

	abstract public void activate(Map<Date, Temperature> records);

}
