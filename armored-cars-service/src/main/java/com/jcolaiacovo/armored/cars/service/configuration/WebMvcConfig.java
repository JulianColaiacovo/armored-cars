package com.jcolaiacovo.armored.cars.service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.jcolaiacovo.armored.cars"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private ObjectMapper objectMapper;

    @Autowired
    public WebMvcConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        jacksonConverter.setObjectMapper(this.objectMapper);
        converters.add(jacksonConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("web/assets/**").addResourceLocations("/components/", "/config/",
                "/app/assets/styles/", "/app/assets/images/", "/dist/", "/app/views/");
    }

}
