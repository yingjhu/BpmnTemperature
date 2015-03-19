package com.diplab.activiti.temperature;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;

public class TestAvgGreaterMode {


	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new DipProcessEngineConfiguration();

		config.setJobExecutorActivate(true);

		ProcessEngine processEngine = config.buildProcessEngine();

		processEngine
				.getRepositoryService()
				.createDeployment()
				.disableSchemaValidation()
				.disableBpmnValidation()
				.addClasspathResource(
						"com/diplab/activiti/temperature/process/ReadTempProcess.bpmn")
				.addClasspathResource(
						"com/diplab/activiti/temperature/process/SchedulerProcess.bpmn")
				.addClasspathResource("avg_greater.bpmn20.xml").deploy();

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"ReadTempProcess");
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"schedulerProcess");

	}
}
