package com.diplab.activiti.temperature.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.junit.Test;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;
import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.db.TemperatureMapper;

public class TestService {

	@Test
	public void testService() {
		DipProcessEngineConfiguration config = new DipProcessEngineConfiguration();
		Set<Class<?>> customMybatisMappers = new HashSet<>();
		customMybatisMappers.add(TemperatureMapper.class);

		config.setCustomMybatisMappers(customMybatisMappers);
		config.setDatabaseSchemaUpdate("drop-create");

		config.buildProcessEngine();

		Temperature temperature2 = new Temperature();
		temperature2.setTemperature(1233.20);
		temperature2.setTime(new Date());

		config.getTemperatureService().insert(temperature2);

		List<Temperature> temperatures = config.getTemperatureService()
				.getTemperatures();

		for (Temperature temperature : temperatures) {
			System.out.println(temperature);
		}
	}

	@Test
	public static void main(String[] args) {
		DipProcessEngineConfiguration config = new DipProcessEngineConfiguration();
		Set<Class<?>> customMybatisMappers = new HashSet<>();
		customMybatisMappers.add(TemperatureMapper.class);

		config.setCustomMybatisMappers(customMybatisMappers);
		config.setDatabaseSchemaUpdate("drop-create");
		config.setJobExecutorActivate(true);

		ProcessEngine processEngine = config.buildProcessEngine();
		processEngine
				.getRepositoryService()
				.createDeployment()
				.disableSchemaValidation()
				.disableBpmnValidation()
				.addClasspathResource(
						"com/diplab/activiti/temperature/process/ReadTempProcess.bpmn")
				// .addClasspathResource(
				// "com/diplab/activiti/temperature/process/SchedulerProcess.bpmn")
				// .addClasspathResource(
				// "temperature.bpmn20.xml")
				.deploy();

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"ReadTempProcess");
		// processEngine.getRuntimeService().startProcessInstanceByKey(
		// "schedulerProcess");

	}
}
