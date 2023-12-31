package com.rk.hibernate.features.datasource.config;


import com.rk.hibernate.features.constants.DBConstants;
import com.rk.hibernate.features.datasource.properties.OneDataSourceProperties;
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
@EnableJpaRepositories(entityManagerFactoryRef ="oneEntityManagerFactory",
        transactionManagerRef="oneTransactionManager",
        basePackages = {"com.rk.hibernate.features.one.repository"})
public class OneDatabaseConfiguration {

    @Autowired
    private OneDataSourceProperties oneDataSourceProperties;


    @Bean(name = "oneDataSource")
    public DataSource oneDataSource() {
        Properties dataSourceProperties = new Properties();
        dataSourceProperties.put(DBConstants.DATASOURCE_URL, oneDataSourceProperties.getUrl());
        dataSourceProperties.put(DBConstants.DATASOURCE_USERNAME, oneDataSourceProperties.getUsername());
        dataSourceProperties.put(DBConstants.DATASOURCE_PASSWORD, oneDataSourceProperties.getPassword());

        HikariConfig hikariConfig = new HikariConfig(dataSourceProperties);
        hikariConfig.setDataSourceClassName(oneDataSourceProperties.getDriverClassName());
        hikariConfig.setIdleTimeout(oneDataSourceProperties.getIdleTimeout());
        hikariConfig.setMinimumIdle(oneDataSourceProperties.getMinIdle());
        hikariConfig.setMaximumPoolSize(oneDataSourceProperties.getMaxPoolSize());
        hikariConfig.setMaxLifetime(oneDataSourceProperties.getMaxLifeTime());
        hikariConfig.setConnectionTimeout(oneDataSourceProperties.getConnectionTimeOut());
        hikariConfig.setPoolName(oneDataSourceProperties.getPoolName());
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "oneEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oneEntityManagerFactory(
            @Qualifier("oneDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.rk.hibernate.features.one.domain");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String,Object> properties = new HashMap<>();
        properties.put(DBConstants.HIBERNATE_DIALECT, oneDataSourceProperties.getHibernateDialect());
        properties.put(DBConstants.SHOW_SQL, oneDataSourceProperties.isShowSql());
        properties.put(DBConstants.DDL, oneDataSourceProperties.getDdl());
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "oneTransactionManager")
    public JpaTransactionManager oneTransactionManager(
            @Qualifier("oneEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
