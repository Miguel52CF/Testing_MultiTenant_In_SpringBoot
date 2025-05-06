package com.miguelcastro.testing.config.tenant;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
public class DataSourceConfig {

  @Bean
  public DataSource dataSource() {
    AbstractRoutingDataSource dataSource = new AbstractRoutingDataSource() {
      @Override
      protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
      }
    };

    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put("swing", createDataSource("swing"));
    targetDataSources.put("test", createDataSource("test"));

    dataSource.setTargetDataSources(targetDataSources);
    dataSource.setDefaultTargetDataSource(createDataSource("swing")); // Por defecto, swing

    return dataSource;
  }

  private DataSource createDataSource(String tenant) {
    String url = "jdbc:postgresql://localhost:5432/bd_" + tenant;
    String username = "root";
    String password = "root";

    return DataSourceBuilder.create()
        .url(url)
        .username(username)
        .password(password)
        .driverClassName("org.postgresql.Driver")
        .build();
  }
}
