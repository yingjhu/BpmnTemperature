package com.diplab.temperature;

import java.util.HashMap;
import java.util.Map;

import org.activiti.bpmn.converter.child.BaseChildElementParser;

import com.diplab.activiti.bpmn.converter.child.ConditionParser;
import com.diplab.activiti.bpmn.converter.child.DiplabEventDefinitionParser;
import com.diplab.activiti.bpmn.converter.child.ModeParser;

public class Constant {
	private Constant() {
	}

	public static Map<String, BaseChildElementParser> DIP_PARSER;

	static {
		DIP_PARSER = new HashMap<String, BaseChildElementParser>();
		BaseChildElementParser parser = new DiplabEventDefinitionParser();
		BaseChildElementParser modeparser = new ModeParser();
		BaseChildElementParser conditionparser = new ConditionParser();
		DIP_PARSER.put(parser.getElementName(), parser);
		DIP_PARSER.put(modeparser.getElementName(), modeparser);
		DIP_PARSER.put(conditionparser.getElementName(), conditionparser);
	}

	public static final String PROPERTYNAME_START_TEMP = "start_temp";
}
