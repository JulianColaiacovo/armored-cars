package com.jcolaiacovo.armored.cars.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ViewResolverConfig {

    @Bean
    public ViewResolver viewResolver(ResourceLoader resourceLoader) {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/web/assets/layouts/");
        vr.setSuffix(".html");
        vr.setCache(false);
        vr.setContentType("text/html;charset=utf-8");
        return vr;
    }
    
}
