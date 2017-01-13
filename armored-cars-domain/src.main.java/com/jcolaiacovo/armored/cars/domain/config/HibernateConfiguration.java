package com.jcolaiacovo.armored.cars.domain.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.util.Properties;

/**
 * Created by Julian on 04/01/2017.
 */
@Configuration
public class HibernateConfiguration {

    @Value(value = "${hibernate.dialect}")
    private String dialect;

    @Value(value = "${hibernate.show_sql}")
    private String showSql;

    @Value(value = "${hibernate.format_sql}")
    private String formatSql;

    @Value(value = "${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean(name = "hibernateProperties")
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", this.dialect);
        hibernateProperties.setProperty("hibernate.show_sql", this.showSql);
        hibernateProperties.setProperty("hibernate.format_sql", this.formatSql);
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", this.hbm2ddlAuto);
        return hibernateProperties;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean localSessionFactoryBean(DriverManagerDataSource driverManagerDataSource,
                                                           @Qualifier(value = "hibernateProperties") Properties hibernateProperties) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(driverManagerDataSource);
        localSessionFactoryBean.setPackagesToScan(
                "com.jcolaiacovo.armored.cars.domain.dao",
                "com.jcolaiacovo.armored.cars.domain.model"
        );
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean;
    }

}
