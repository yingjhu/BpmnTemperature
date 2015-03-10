package com.diplab.temperature.delegate;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.temperature.RecordsUtil;
import com.diplab.temperature.TemperatureReceiver;
import com.diplab.temperature.TemperatureReceiverImp;

public class ReadTempTask implements JavaDelegate {
	TemperatureReceiver receiver = new TemperatureReceiverImp();

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(ReadTempTask.class.getName());
		while (true) {
			RecordsUtil.records.put(new Date(), receiver.getTemperature());
		}
	}

}
