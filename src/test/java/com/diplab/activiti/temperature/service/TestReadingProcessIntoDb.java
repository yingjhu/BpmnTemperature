package com.diplab.activiti.temperature.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.activiti.engine.ProcessEngine;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.diplab.activiti.engine.impl.cfg.DipInMemProcessEngineConfiguration;
import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;
import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.db.TemperatureMapper;

public class TestReadingProcessIntoDb {

	@SuppressWarnings("deprecation")
	@Test
	public void testInsert() {
		DipProcessEngineConfiguration config = new DipInMemProcessEngineConfiguration();
		Set<Class<?>> customMybatisMappers = new HashSet<>();
		customMybatisMappers.add(TemperatureMapper.class);

		config.setCustomMybatisMappers(customMybatisMappers);
		config.setDatabaseSchemaUpdate("drop-create");

		config.buildProcessEngine();

		Temperature temperature2 = new Temperature();
		temperature2.setTemperature(1233.20);
		Date time = new Date();
		temperature2.setTime(time);

		config.getTemperatureService().insert(temperature2);

		List<Temperature> temperatures = config.getTemperatureService()
				.getTemperatures();

		Assert.assertEquals(temperatures.get(0).getTemperature(), 1233.20);
		Assert.assertEquals(temperatures.get(0).getTime(), time);

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
