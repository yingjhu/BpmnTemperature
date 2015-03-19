package com.diplab.activiti.temperature.db;

import java.util.Date;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.junit.Assert;
import org.junit.Test;

import com.diplab.activiti.engine.impl.cfg.DipInMemProcessEngineConfiguration;
import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;
import com.diplab.activiti.temperature.Temperature;

public class TestReadingProcessIntoDb {

	@Test
	public void testInsert() {
		DipProcessEngineConfiguration config = new DipInMemProcessEngineConfiguration();
		config.buildProcessEngine();

		Temperature temp = new Temperature();
		temp.setTemperature(1233.20);
		Date time = new Date();
		temp.setTime(time);

		config.getTemperatureService().insert(temp);

		List<Temperature> temperatures = config.getTemperatureService()
				.getTemperatures();

		Assert.assertEquals(temperatures.size(), 1);
		Assert.assertEquals(temperatures.get(0).getTemperature(), 1233.20, 0);
		Assert.assertEquals(temperatures.get(0).getTime(), time);

	}

	public static void main(String[] args) {
		DipProcessEngineConfiguration config = new DipProcessEngineConfiguration();
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
