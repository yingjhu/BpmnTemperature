package com.diplab.activiti.bpmn.converter.child;

import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.child.BaseChildElementParser;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;

import com.diplab.activiti.bpmn.model.DiplabEventDefinition;
import com.diplab.activiti.temperature.Constant;

public class TimeParser extends BaseChildElementParser {

	@Override
	public String getElementName() {
		return Constant.ATTRIBUTE_TIME;
	}

	@Override
	public void parseChildElement(XMLStreamReader xtr,
			BaseElement parentElement, BpmnModel model) throws Exception {

		if (parentElement instanceof DiplabEventDefinition == false)
			return;

		DiplabEventDefinition eventDefinition = (DiplabEventDefinition) parentElement;
		eventDefinition.setTime(xtr.getElementText());

	}
}
