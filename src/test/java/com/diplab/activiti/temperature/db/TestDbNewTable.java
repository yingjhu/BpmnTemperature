package com.diplab.activiti.temperature.db;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.diplab.activiti.engine.impl.cfg.DipInMemProcessEngineConfiguration;
import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;
import com.diplab.activiti.temperature.Temperature;

public class TestDbNewTable {

	public static void main(String[] args) {
	}

	@Test
	public void testMybatis() {
		
	}

	@Test
	public void testDbCreate() {
		ProcessEngineConfigurationImpl configuration = new DipProcessEngineConfiguration();

		Set<Class<?>> customMybatisMappers = new HashSet<>();
		customMybatisMappers.add(TemperatureMapper.class);
		configuration.setCustomMybatisMappers(customMybatisMappers);

		configuration.setDatabaseSchemaUpdate("drop-create");
		configuration.buildProcessEngine();

		configuration.getCommandExecutor().execute(new Command<Void>() {

			@Override
			public Void execute(CommandContext commandContext) {
				SqlSession sqlSession = commandContext.getDbSqlSession()
						.getSqlSession();

				TemperatureMapper mapper = sqlSession
						.getMapper(TemperatureMapper.class);
				mapper.insert(new Temperature(100));
				List<Temperature> allList = mapper.selectAll();

				for (Temperature temp : allList) {
					System.out.println(temp);
				}
				return null;
			}
		});
	}
}
