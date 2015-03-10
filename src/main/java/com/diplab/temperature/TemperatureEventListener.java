package com.diplab.temperature;

import java.util.Date;
import java.util.Map;

public abstract class TemperatureEventListener implements IsSatisfy {

	// delegate
	private IsSatisfy isSatisfy;

	public TemperatureEventListener(IsSatisfy isSatisfy) {
		this.isSatisfy = isSatisfy;
	}

	@Override
	public boolean isSatisfy(Map<Date, Temperature> records) {
		return isSatisfy.isSatisfy(records);
	}

	/**
	 * If {@link #isSatisfy}, this method will be called.
	 * 
	 * @param records
	 */
	public abstract void activate(Map<Date, Temperature> records);

	/**
	 * After {@link #activate(Map)} is called, should trigger again if
	 * {@link #isSatisfy}
	 * 
	 * @return true if it should trigger again
	 */
	public abstract boolean isEnd();

}
