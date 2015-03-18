package com.diplab.activiti.temperature;

import java.util.List;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class RecordsUtil {

	private static ProcessEngineConfigurationImpl configuration;

	private RecordsUtil() {
	}

	public static Temperature getLatest(List<Temperature> records) {

		Temperature[] temperatures = getLatest(records, 1);
		if (temperatures.length == 0)
			return null;
		return temperatures[0];

	}

	public static Temperature[] getLatest(List<Temperature> records, int number) {
		// TODO
		return null;
	}

	public static List<Temperature> getRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void add(Temperature temp) {
		// get DbSqlSession
		// Mapper .. > insert
		// TODO

	}

	public static ProcessEngineConfigurationImpl getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(ProcessEngineConfigurationImpl configuration) {
		RecordsUtil.configuration = configuration;
	}
}
