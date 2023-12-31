package com.rk.hibernate.features.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.cif-datasource")
public class CifDataSourceProperties extends CommonDatasourceProperties{
}
