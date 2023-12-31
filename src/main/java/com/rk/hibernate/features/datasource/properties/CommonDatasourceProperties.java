package com.rk.hibernate.features.datasource.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDatasourceProperties {

    private String url;

    private String username;

    private String password;

    private boolean showSql;

    private Integer maxPoolSize;

    private Integer minIdle;

    private Integer idleTimeout;

    private Integer maxLifeTime;

    private Integer connectionTimeOut;

    private String driverClassName;

    private String poolName;

    private String hibernateDialect;

    private String ddl="none";
}
