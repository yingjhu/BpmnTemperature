package com.diplab.temperature.delegate;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.temperature.RecordsUtil;
import com.diplab.temperature.TemperatureReceiver;

public class TestTask implements JavaDelegate {
	TemperatureReceiver receiver = new TemperatureReceiver();

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(TestTask.class.getName());
	}

}
