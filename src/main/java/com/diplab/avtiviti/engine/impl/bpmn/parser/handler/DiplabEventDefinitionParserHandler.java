package com.diplab.avtiviti.engine.impl.bpmn.parser.handler;

import java.util.Date;
import java.util.Map;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.AbstractBpmnParseHandler;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diplab.activiti.bpmn.model.DiplabEventDefinition;
import com.diplab.activiti.engine.impl.jobexecutor.TemperatureDeclarationImpl;
import com.diplab.activiti.engine.impl.jobexecutor.TemperatureDeclarationType;
import com.diplab.temperature.IsSatisfy;
import com.diplab.temperature.Temperature;
import com.diplab.temperature.TemperatureEventListener;
import com.diplab.temperature.TemperatureEventScheduler;

public class DiplabEventDefinitionParserHandler extends
		AbstractBpmnParseHandler<DiplabEventDefinition> {

	private static final Logger logger = LoggerFactory
			.getLogger(DiplabEventDefinitionParserHandler.class);

	@Override
	protected Class<? extends BaseElement> getHandledType() {
		return DiplabEventDefinition.class;
	}

	@Override
	protected void executeParse(BpmnParse bpmnParse,
			DiplabEventDefinition eventDefinition) {
		// Behavior
		ActivityImpl temperatureEventActivity = bpmnParse.getCurrentActivity();
		temperatureEventActivity.setActivityBehavior(new ActivityBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			public void execute(ActivityExecution execution) throws Exception {
				System.out.println("Temperature");
				execution.take(execution.getActivity().getOutgoingTransitions()
						.get(0));

			}
		});

		// prepare TemperatureEventListener and schedule
		TemperatureDeclarationType type;
		double condition = Double.parseDouble(eventDefinition.getCondition());
		if (eventDefinition.getMode().equalsIgnoreCase("greater")) {
			type = TemperatureDeclarationType.GREATER;
		} else if (eventDefinition.getMode().equalsIgnoreCase("lesser")) {
			type = TemperatureDeclarationType.LESSER;
		} else {
			logger.warn(String.format("%s is not supportted",
					eventDefinition.getMode()));
			return;
		}

		TemperatureDeclarationImpl declarationImpl = new TemperatureDeclarationImpl(
				type, condition);

		IsSatisfy isSatisfy = declarationImpl.prepareIsSatisfy();

		final ProcessDefinitionEntity processDefinition = bpmnParse
				.getCurrentProcessDefinition();

		TemperatureEventListener observer = new TemperatureEventListener(
				isSatisfy) {

			@Override
			public void activate(Map<Date, Temperature> records) {
				processDefinition.createProcessInstance().start();
			}
		};
		TemperatureEventScheduler.getTemperatureEventScheduler()
				.addTemperatureEventObserver(observer);

	}

}
