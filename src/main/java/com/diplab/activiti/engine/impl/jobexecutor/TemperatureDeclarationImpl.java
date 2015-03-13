package com.diplab.activiti.engine.impl.jobexecutor;

import java.util.Date;
import java.util.Map;

import com.diplab.activiti.temperature.IsSatisfy;
import com.diplab.activiti.temperature.RecordsUtil;
import com.diplab.activiti.temperature.Temperature;

public class TemperatureDeclarationImpl {
	private TemperatureDeclarationType type;
	private double condition;

	public TemperatureDeclarationImpl(TemperatureDeclarationType type,
			double condition) {
		super();
		this.type = type;
		this.condition = condition;
	}

	/**
	 * @return null if mode is not defined.
	 */
	public IsSatisfy prepareIsSatisfy() {
		switch (type) {
		case GREATER:
			return new IsSatisfy() {

				@Override
				public boolean isSatisfy(Map<Date, Temperature> records) {
					if (records == null || records.size() == 0)
						return false;
					return RecordsUtil.getLatest(records).getTemperature() > condition;
				}
			};

		case LESSER:
			return new IsSatisfy() {

				@Override
				public boolean isSatisfy(Map<Date, Temperature> records) {
					if (records == null || records.size() == 0)
						return false;
					return RecordsUtil.getLatest(records).getTemperature() < condition;
				}
			};
		default:
			// throw new Exception(String.format("%s is not supported",
			// type.toString()));
			return null;
		}

	}
}
