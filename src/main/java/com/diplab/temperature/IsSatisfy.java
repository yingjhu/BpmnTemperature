package com.diplab.temperature;

import java.util.Date;
import java.util.Map;

public interface IsSatisfy {
	boolean isSatisfy(Map<Date, Temperature> records);
}
