package com.rk.hibernate.features.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.one-datasource")
public class OneDataSourceProperties extends CommonDatasourceProperties{
}
