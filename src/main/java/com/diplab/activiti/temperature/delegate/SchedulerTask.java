package com.diplab.activiti.temperature.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.temperature.RecordsUtil;
import com.diplab.activiti.temperature.TemperatureEventListener;

public class SchedulerTask implements JavaDelegate {
	static List<TemperatureEventListener> listeners = Collections
			.synchronizedList(new ArrayList<TemperatureEventListener>());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(SchedulerTask.class.getName());
		while (true) {
			for (Iterator<TemperatureEventListener> iterator = listeners
					.iterator(); iterator.hasNext();) {
				TemperatureEventListener temperatureEventListener = iterator
						.next();
				if (temperatureEventListener.isSatisfy(RecordsUtil.records)) {
					temperatureEventListener.activate(RecordsUtil.records);
					if (temperatureEventListener.isEnd()) {
						iterator.remove();
					}
				}

			}
		}
	}

	public static void addTemperatureEventListener(
			TemperatureEventListener listener) {
		listeners.add(listener);
	}

}
