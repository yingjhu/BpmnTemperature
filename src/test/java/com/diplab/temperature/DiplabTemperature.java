package com.diplab.temperature;

import java.util.Arrays;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.parse.BpmnParseHandler;

import com.diplab.activiti.bpmn.converter.DiplabStartEventXMLConverter;
import com.diplab.avtiviti.engine.impl.bpmn.parser.handler.DiplabEventDefinitionParserHandler;
import com.diplab.avtiviti.engine.impl.bpmn.parser.handler.DiplabStartEventParserHandler;

public class DiplabTemperature {

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl standaloneInMemProcessEngineConfiguration = (ProcessEngineConfigurationImpl) ProcessEngineConfiguration
				.createStandaloneInMemProcessEngineConfiguration();

		BpmnXMLConverter.addConverter(new DiplabStartEventXMLConverter());

		standaloneInMemProcessEngineConfiguration
				.setCustomDefaultBpmnParseHandlers(Arrays
						.<BpmnParseHandler> asList(new DiplabStartEventParserHandler()));

		standaloneInMemProcessEngineConfiguration
				.setPostBpmnParseHandlers(Arrays
						.<BpmnParseHandler> asList(new DiplabEventDefinitionParserHandler()));

		// standaloneInMemProcessEngineConfiguration.setCustomPostDeployers(customPostDeployers)

		standaloneInMemProcessEngineConfiguration.setJobExecutorActivate(true);

		TemperatureEventScheduler.getTemperatureEventScheduler().start();

		ProcessEngine processEngine = standaloneInMemProcessEngineConfiguration
				.buildProcessEngine();

		processEngine.getRepositoryService().createDeployment()
				.disableSchemaValidation().disableBpmnValidation()
				.addClasspathResource("Temperature.bpmn20.xml").deploy();

	}
}
