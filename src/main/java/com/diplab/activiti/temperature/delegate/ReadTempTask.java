package com.diplab.activiti.temperature.delegate;

import java.util.Date;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.context.Context;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;
import com.diplab.activiti.temperature.RecordsUtil;
import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.TemperatureReceiver;
import com.diplab.activiti.temperature.TemperatureReceiverImp;

public class ReadTempTask implements JavaDelegate {
	TemperatureReceiver receiver = new TemperatureReceiverImp();

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(ReadTempTask.class.getName());
		while (true) {
			Temperature temp = new Temperature();
			temp.setTemperature(30 + 15 * Math.random());
			temp.setTime(new Date());
			((DipProcessEngineConfiguration) ProcessEngines
					.getDefaultProcessEngine().getProcessEngineConfiguration()
					.getProcessEngineConfiguration()).getTemperatureService()
					.insert(temp);
		}
	}
}
