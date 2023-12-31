package com.rk.hibernate.features.datasource.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.two-datasource")
@Data
public class TwoDataSourceProperties extends CommonDatasourceProperties {
}
