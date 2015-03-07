package com.diplab.temperature.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.Marshaller.Listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.temperature.RecordsUtil;
import com.diplab.temperature.TemperatureEventListener;

public class SchedulerTask implements JavaDelegate {
	static List<TemperatureEventListener> listeners = Collections
			.synchronizedList(new ArrayList<TemperatureEventListener>());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(SchedulerTask.class.getName());
		while (true) {
			for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
				TemperatureEventListener temperatureEventListener = (TemperatureEventListener) iterator
						.next();
				if (temperatureEventListener.isSatisfy(RecordsUtil.records)) {
					temperatureEventListener.activate(RecordsUtil.records);
					iterator.remove();
				}

			}
		}
	}

	public static void addTemperatureEventListener(
			TemperatureEventListener listener) {
		listeners.add(listener);
	}

}
