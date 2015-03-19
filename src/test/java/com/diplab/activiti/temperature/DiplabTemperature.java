package com.diplab.activiti.temperature;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;

public class DiplabTemperature {

	public static ProcessEngine processEngine;

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new DipProcessEngineConfiguration();

		config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();
		DiplabTemperature.processEngine = processEngine;

		processEngine
				.getRepositoryService()
				.createDeployment()
				.disableSchemaValidation()
				.disableBpmnValidation()
				.addClasspathResource(
						"com/diplab/activiti/temperature/process/ReadTempProcess.bpmn")
				.addClasspathResource(
						"com/diplab/activiti/temperature/process/SchedulerProcess.bpmn")
				.addClasspathResource("temperature.bpmn20.xml").deploy();

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"ReadTempProcess");
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"schedulerProcess");

	}
}
