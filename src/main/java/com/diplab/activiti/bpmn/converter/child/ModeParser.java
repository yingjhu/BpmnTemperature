package com.diplab.activiti.bpmn.converter.child;

import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.child.BaseChildElementParser;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;

import com.diplab.activiti.bpmn.model.DiplabEventDefinition;

public class ModeParser extends BaseChildElementParser {
	
	public static final String ATTRIBUTE_MODE = "mode";

	@Override
	public String getElementName() {
		return ATTRIBUTE_MODE;
	}

	@Override
	public void parseChildElement(XMLStreamReader xtr,
			BaseElement parentElement, BpmnModel model) throws Exception {
		
		if (parentElement instanceof DiplabEventDefinition == false) return;
	    
	    DiplabEventDefinition eventDefinition = (DiplabEventDefinition) parentElement;
	    eventDefinition.setMode(xtr.getElementText());

	}

}
