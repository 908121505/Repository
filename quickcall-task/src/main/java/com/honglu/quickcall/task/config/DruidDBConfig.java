package com.honglu.quickcall.task.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableConfigurationProperties(DruidSettings.class)
public class DruidDBConfig {

	@Autowired
	private DruidSettings druidSettings;

	@Bean 
	@Primary
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(druidSettings.getUrl());
		datasource.setUsername(druidSettings.getUsername());
		datasource.setPassword(druidSettings.getPassword());
		datasource.setDriverClassName(druidSettings.getDriverClassName());

		// configuration
		datasource.setInitialSize(druidSettings.getInitialSize());
		datasource.setMinIdle(druidSettings.getMinIdle());
		datasource.setMaxActive(druidSettings.getMaxActive());
		datasource.setMaxWait(druidSettings.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
		String validationQuery = druidSettings.getValidationQuery();
		if (validationQuery != null && !"".equals(validationQuery)) {
			datasource.setValidationQuery(validationQuery);
		}
		datasource.setTestWhileIdle(druidSettings.isTestWhileIdle());
		datasource.setTestOnBorrow(druidSettings.isTestOnBorrow());
		datasource.setTestOnReturn(druidSettings.isTestOnReturn());
		datasource.setUseGlobalDataSourceStat(druidSettings.isUseGlobalDataSourceStat());
		if (druidSettings.isPoolPreparedStatements()) {
			datasource.setMaxPoolPreparedStatementPerConnectionSize(
					druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
		}
		datasource.setMaxPoolPreparedStatementPerConnectionSize(
				druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
		try {
			datasource.setFilters(druidSettings.getFilters());
		} catch (SQLException e) {
		}
		datasource.setConnectionProperties(druidSettings.getConnectionProperties());

		return datasource;
	}

}
