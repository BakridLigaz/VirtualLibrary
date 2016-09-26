package com.ligaz.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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
    private static final String HIBERNATE_PROPERTIES_PATH ="properties/hibernate.properties";


    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(env.getRequiredProperty(DB_URL));
        dataSource.setDriverClassName(env.getRequiredProperty(DB_DRIVER));
        dataSource.setUsername(env.getRequiredProperty(DB_USER));
        dataSource.setPassword(env.getRequiredProperty(DB_PASSWORD));
        return dataSource;
    }

    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan(env.getRequiredProperty(DB_PACKAGE_TO_SCAN));
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaProperties(getJpaProperties());
        return null;
    }

    private Properties getJpaProperties() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getResourceAsStream(HIBERNATE_PROPERTIES_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
           throw new IllegalArgumentException("Properties 'hibernate.properties' not found",e);
        }
        return null;
    }


}
