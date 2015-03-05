package com.diplab.activiti.bpmn.converter.child;

import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.child.BaseChildElementParser;
import org.activiti.bpmn.converter.util.BpmnXMLUtil;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Event;

import com.diplab.activiti.bpmn.model.DiplabEventDefinition;
import com.diplab.temperature.Constant;

public class DiplabEventDefinitionParser extends BaseChildElementParser {

	public static final String ELEMENT_DIPLAB_EVENT_DEFINITION = "diplabEventDefinition";

	@Override
	public String getElementName() {

		return ELEMENT_DIPLAB_EVENT_DEFINITION;
	}

	@Override
	public void parseChildElement(XMLStreamReader xtr,
			BaseElement parentElement, BpmnModel model) throws Exception {
		if (parentElement instanceof Event == false)
			return;

		DiplabEventDefinition diplabEventDefinition = new DiplabEventDefinition();
		BpmnXMLUtil.addXMLLocation(diplabEventDefinition, xtr);
		BpmnXMLUtil.parseChildElements(ELEMENT_DIPLAB_EVENT_DEFINITION,
				diplabEventDefinition, xtr, Constant.DIP_PARSER, model);

		((Event) parentElement).getEventDefinitions()
				.add(diplabEventDefinition);
	}

}
