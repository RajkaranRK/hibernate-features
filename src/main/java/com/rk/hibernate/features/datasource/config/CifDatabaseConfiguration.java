package com.rk.hibernate.features.datasource.config;


import com.rk.hibernate.features.constants.DBConstants;
import com.rk.hibernate.features.datasource.properties.CifDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef ="cifEntityManagerFactory",
        transactionManagerRef="cifTransactionManager",
        basePackages = {"com.rk.hibernate.features.cif.repository"})
public class CifDatabaseConfiguration {

    @Autowired
    private CifDataSourceProperties cifDataSourceProperties;


    @Bean(name = "cifDataSource")
    public DataSource dataSource() {
        Properties dataSourceProperties = new Properties();
        dataSourceProperties.put(DBConstants.DATASOURCE_URL, cifDataSourceProperties.getUrl());
        dataSourceProperties.put(DBConstants.DATASOURCE_USERNAME, cifDataSourceProperties.getUsername());
        dataSourceProperties.put(DBConstants.DATASOURCE_PASSWORD, cifDataSourceProperties.getPassword());

        HikariConfig hikariConfig = new HikariConfig(dataSourceProperties);
        hikariConfig.setDataSourceClassName(cifDataSourceProperties.getDriverClassName());
        hikariConfig.setIdleTimeout(cifDataSourceProperties.getIdleTimeout());
        hikariConfig.setMinimumIdle(cifDataSourceProperties.getMinIdle());
        hikariConfig.setMaximumPoolSize(cifDataSourceProperties.getMaxPoolSize());
        hikariConfig.setMaxLifetime(cifDataSourceProperties.getMaxLifeTime());
        hikariConfig.setConnectionTimeout(cifDataSourceProperties.getConnectionTimeOut());
        hikariConfig.setPoolName(cifDataSourceProperties.getPoolName());
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "cifEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cifEntityManagerFactory(
            @Qualifier("cifDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.rk.hibernate.features.cif.domain");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String,Object> properties = new HashMap<>();
        properties.put(DBConstants.HIBERNATE_DIALECT,cifDataSourceProperties.getHibernateDialect());
        properties.put(DBConstants.SHOW_SQL,cifDataSourceProperties.isShowSql());
        properties.put(DBConstants.DDL, cifDataSourceProperties.getDdl());
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "cifTransactionManager")
    public JpaTransactionManager cifTransactionManager(
            @Qualifier("cifEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
