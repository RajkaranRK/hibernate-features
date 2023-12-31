package com.rk.hibernate.features.datasource.config;

import com.rk.hibernate.features.constants.DBConstants;
import com.rk.hibernate.features.datasource.properties.TwoDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
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
@EnableJpaRepositories(entityManagerFactoryRef ="twoEntityManagerFactory",
        transactionManagerRef="twoTransactionManager",
basePackages = {"com.rk.hibernate.features.two.repository"})
public class TwoDatabaseConfiguration {

    @Autowired
    private TwoDataSourceProperties twoDataSourceProperties;


    @Bean(name = "twoDataSource")
    @Primary
    public DataSource twoDataSource() {
        Properties dataSourceProperties = new Properties();
        dataSourceProperties.put(DBConstants.DATASOURCE_URL, twoDataSourceProperties.getUrl());
        dataSourceProperties.put(DBConstants.DATASOURCE_USERNAME, twoDataSourceProperties.getUsername());
        dataSourceProperties.put(DBConstants.DATASOURCE_PASSWORD, twoDataSourceProperties.getPassword());

        HikariConfig hikariConfig = new HikariConfig(dataSourceProperties);
        hikariConfig.setDataSourceClassName(twoDataSourceProperties.getDriverClassName());
        hikariConfig.setIdleTimeout(twoDataSourceProperties.getIdleTimeout());
        hikariConfig.setMinimumIdle(twoDataSourceProperties.getMinIdle());
        hikariConfig.setMaximumPoolSize(twoDataSourceProperties.getMaxPoolSize());
        hikariConfig.setMaxLifetime(twoDataSourceProperties.getMaxLifeTime());
        hikariConfig.setConnectionTimeout(twoDataSourceProperties.getConnectionTimeOut());
        hikariConfig.setPoolName(twoDataSourceProperties.getPoolName());
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "twoEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean twoEntityManagerFactory(
            @Qualifier("twoDataSource") DataSource dataSource,
            HibernateProperties hibernateProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.rk.hibernate.features.two.domain");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties = new HashMap<>();
        properties.put(DBConstants.HIBERNATE_DIALECT, twoDataSourceProperties.getHibernateDialect());
        properties.put(DBConstants.SHOW_SQL, twoDataSourceProperties.isShowSql());
        properties.put(DBConstants.DDL, twoDataSourceProperties.getDdl());
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "twoTransactionManager")
    @Primary
    public JpaTransactionManager twoTransactionManager(
            @Qualifier("twoEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
