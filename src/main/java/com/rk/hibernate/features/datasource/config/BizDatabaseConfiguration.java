package com.rk.hibernate.features.datasource.config;

import com.rk.hibernate.features.constants.DBConstants;
import com.rk.hibernate.features.datasource.properties.BizDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef ="bizEntityManagerFactory",
        transactionManagerRef="bizTransactionManager",
basePackages = {"com.rk.hibernate.features.biz.repository"})
public class BizDatabaseConfiguration {

    @Autowired
    private BizDataSourceProperties bizDataSourceProperties;


    @Bean(name = "bizDataSource")
    @Primary
    public DataSource dataSource() {
        Properties dataSourceProperties = new Properties();
        dataSourceProperties.put(DBConstants.DATASOURCE_URL, bizDataSourceProperties.getUrl());
        dataSourceProperties.put(DBConstants.DATASOURCE_USERNAME,bizDataSourceProperties.getUsername());
        dataSourceProperties.put(DBConstants.DATASOURCE_PASSWORD,bizDataSourceProperties.getPassword());

        HikariConfig hikariConfig = new HikariConfig(dataSourceProperties);
        hikariConfig.setDataSourceClassName(bizDataSourceProperties.getDriverClassName());
        hikariConfig.setIdleTimeout(bizDataSourceProperties.getIdleTimeout());
        hikariConfig.setMinimumIdle(bizDataSourceProperties.getMinIdle());
        hikariConfig.setMaximumPoolSize(bizDataSourceProperties.getMaxPoolSize());
        hikariConfig.setMaxLifetime(bizDataSourceProperties.getMaxLifeTime());
        hikariConfig.setConnectionTimeout(bizDataSourceProperties.getConnectionTimeOut());
        hikariConfig.setPoolName(bizDataSourceProperties.getPoolName());
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "bizEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean bizEntityManagerFactory(
            @Qualifier("bizDataSource") DataSource dataSource,
            HibernateProperties hibernateProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.rk.hibernate.features.biz.domain");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties = new HashMap<>();
        properties.put(DBConstants.HIBERNATE_DIALECT,bizDataSourceProperties.getHibernateDialect());
        properties.put(DBConstants.SHOW_SQL,bizDataSourceProperties.isShowSql());
        properties.put(DBConstants.DDL, bizDataSourceProperties.getDdl());
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "bizTransactionManager")
    @Primary
    public JpaTransactionManager bizTransactionManager(
            @Qualifier("bizEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
