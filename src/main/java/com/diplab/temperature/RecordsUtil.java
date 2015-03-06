package com.diplab.temperature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RecordsUtil {

	private RecordsUtil() {
	}

	public static Temperature getLatest(Map<Date, Temperature> records) {

		Temperature[] temperatures = getLatest(records, 1);
		if (temperatures.length == 0)
			return null;
		return temperatures[0];

	}

	public static Temperature[] getLatest(Map<Date, Temperature> records,
			int number) {
		List<Temperature> list = new ArrayList<>();
		for (Date date : records.keySet()) {
			if (list.size() >= number)
				break;
			list.add(records.get(date));
		}
		return list.toArray(new Temperature[] {});

	}
}
