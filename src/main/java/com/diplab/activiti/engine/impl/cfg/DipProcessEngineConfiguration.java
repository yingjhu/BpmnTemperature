package com.diplab.activiti.engine.impl.cfg;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.diplab.activiti.engine.impl.TemperatureServiceImpl;

public class DipProcessEngineConfiguration extends
		StandaloneProcessEngineConfiguration {

	TemperatureServiceImpl temperatureService = new TemperatureServiceImpl(this);

	// Override initService
	@Override
	protected void initServices() {
		super.initServices();
		initService(temperatureService);
	};

	// getter and setter for TemperatureService

	public TemperatureServiceImpl getTemperatureService() {
		return temperatureService;
	}

	public void setTemperatureService(TemperatureServiceImpl temperatureService) {
		this.temperatureService = temperatureService;
	}

	@Override
	public ProcessEngine buildProcessEngine() {
		init();
		// new out tables
		commandExecutor.execute(getSchemaCommandConfig(), new Command<Void>() {

			@Override
			public Void execute(CommandContext commandContext) {
				DbSqlSession session = commandContext
						.getSession(DbSqlSession.class);
				session.executeSchemaResource(
						"create",
						"temperature",
						"com/diplab/activiti/temperature/db/create/activiti.h2.create.temperature.sql",
						false);
				return null;
			}
		});
		return new ProcessEngineImpl(this);
	}

}
