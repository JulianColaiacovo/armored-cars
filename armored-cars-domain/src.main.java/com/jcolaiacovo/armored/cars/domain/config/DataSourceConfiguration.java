package com.jcolaiacovo.armored.cars.domain.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Julian on 09/01/2017.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:conf/env/environment.properties")
public class DataSourceConfiguration {

    @Value(value = "${armored.cars.datasource.driver.class.name}")
    private String driverClass;

    @Value(value = "${armored.cars.datasource.url}")
    private String url;

    @Value(value = "${armored.cars.datasource.username}")
    private String userName;

    @Value(value = "${armored.cars.datasource.password}")
    private String password;

    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(this.driverClass);
        driverManagerDataSource.setUrl(this.url);
        driverManagerDataSource.setUsername(this.userName);
        driverManagerDataSource.setPassword(this.password);
        return driverManagerDataSource;
    }

    @Bean("transactionManager")
    public HibernateTransactionManager hibernateTransactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
