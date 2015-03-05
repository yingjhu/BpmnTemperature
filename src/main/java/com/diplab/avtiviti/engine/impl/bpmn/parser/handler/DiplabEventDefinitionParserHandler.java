package com.diplab.avtiviti.engine.impl.bpmn.parser.handler;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.AbstractBpmnParseHandler;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diplab.activiti.bpmn.model.DiplabEventDefinition;

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

		ActivityImpl temperatureEventActivity = bpmnParse.getCurrentActivity();

		if (eventDefinition instanceof DiplabEventDefinition) {
			temperatureEventActivity
					.setActivityBehavior(new ActivityBehavior() {
						private static final long serialVersionUID = 1L;

						@Override
						public void execute(ActivityExecution execution)
								throws Exception {
							System.out.println("Temperature");

							execution.take(execution.getActivity()
									.getOutgoingTransitions().get(0));

						}
					});
		} else {
			logger.warn("Unsupported event definition on start event",
					eventDefinition);
		}
	}

}
