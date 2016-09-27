package com.ligaz.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.ligaz.server.repository")
@EnableTransactionManagement
@ComponentScan("com.ligaz.server")
@PropertySource("classpath:properties/db.properties")
public class DatabaseConfig {

    private static final String DB_DRIVER = "db.driver";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_PACKAGE_TO_SCAN ="db.entity.package";
    private static final String DB_INITIAL_SIZE = "db.initialSize";
    private static final String DB_MIN_IDLE = "db.minIdle";
    private static final String DB_MAX_IDLE = "db.maxIdle";
    private static final String DB_TIME_BEETWEEN_EVICTION_RUNS_MILLIS = "db.timeBetweenEvictionRunsMillis";
    private static final String DB_MIN_EVICTABLE_IDLE_TIME_MILLIS = "db.minEvictableIdleTimeMillis";
    private static final String DB_TEST_ON_BORROW = "db.testOnBorrow";
    private static final String DB_VALIDATION_QUERY = "db.validationQuery";
    private static final String HIBERNATE_PROPERTIES_PATH ="properties/hibernate.properties";


    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan(env.getRequiredProperty(DB_PACKAGE_TO_SCAN));
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaProperties(getJpaProperties());
        return entityManager;
    }

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(env.getRequiredProperty(DB_URL));
        dataSource.setDriverClassName(env.getRequiredProperty(DB_DRIVER));
        dataSource.setUsername(env.getRequiredProperty(DB_USER));
        dataSource.setPassword(env.getRequiredProperty(DB_PASSWORD));
        dataSource.setInitialSize(Integer.parseInt(env.getRequiredProperty(DB_INITIAL_SIZE)));
        dataSource.setMinIdle(Integer.parseInt(env.getRequiredProperty(DB_MIN_IDLE)));
        dataSource.setMaxIdle(Integer.parseInt(env.getRequiredProperty(DB_MAX_IDLE)));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getRequiredProperty(DB_TIME_BEETWEEN_EVICTION_RUNS_MILLIS)));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getRequiredProperty(DB_MIN_EVICTABLE_IDLE_TIME_MILLIS)));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getRequiredProperty(DB_TEST_ON_BORROW)));
        dataSource.setValidationQuery(env.getRequiredProperty(DB_VALIDATION_QUERY));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties getJpaProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(HIBERNATE_PROPERTIES_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
           throw new IllegalArgumentException("Properties 'hibernate.properties' not found",e);
        }
        return properties;
    }


}
