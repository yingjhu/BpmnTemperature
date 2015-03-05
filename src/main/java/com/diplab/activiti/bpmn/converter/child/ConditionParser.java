package com.diplab.activiti.bpmn.converter.child;

import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.child.BaseChildElementParser;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;

import com.diplab.activiti.bpmn.model.DiplabEventDefinition;

public class ConditionParser extends BaseChildElementParser {
	
	public static final String ATTRIBUTE_CONDITION = "condition";

	@Override
	public String getElementName() {
		return ATTRIBUTE_CONDITION;
	}

	@Override
	public void parseChildElement(XMLStreamReader xtr,
			BaseElement parentElement, BpmnModel model) throws Exception {
		
		if (parentElement instanceof DiplabEventDefinition == false) return;
	    
	    DiplabEventDefinition eventDefinition = (DiplabEventDefinition) parentElement;
	    eventDefinition.setCondition(xtr.getElementText());

	}
}
