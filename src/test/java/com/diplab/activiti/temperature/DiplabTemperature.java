package com.diplab.activiti.temperature;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.parse.BpmnParseHandler;

import com.diplab.activiti.bpmn.converter.DiplabStartEventXMLConverter;
import com.diplab.activiti.engine.impl.bpmn.parser.handler.DiplabEventDefinitionParserHandler;
import com.diplab.activiti.engine.impl.bpmn.parser.handler.DiplabStartEventParserHandler;
import com.diplab.activiti.temperature.db.TemperatureMapper;

public class DiplabTemperature {

	public static ProcessEngine processEngine;

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration();

		BpmnXMLConverter.addConverter(new DiplabStartEventXMLConverter());

		config.setCustomDefaultBpmnParseHandlers(Arrays
				.<BpmnParseHandler> asList(new DiplabStartEventParserHandler()));

		config.setPostBpmnParseHandlers(Arrays
				.<BpmnParseHandler> asList(new DiplabEventDefinitionParserHandler()));

		// standaloneInMemProcessEngineConfiguration.setCustomPostDeployers(customPostDeployers)

		config.setJobExecutorActivate(true);

		Set<Class<?>> customMybatisMappers = new HashSet<>();
		customMybatisMappers.add(TemperatureMapper.class);

		config.setCustomMybatisMappers(customMybatisMappers);
		config.setDatabaseSchemaUpdate("drop-create");

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
				.addClasspathResource("temperature.bpmn20.xml")
				.deploy();

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"ReadTempProcess");
		 processEngine.getRuntimeService().startProcessInstanceByKey(
		 "schedulerProcess");

	}
}
