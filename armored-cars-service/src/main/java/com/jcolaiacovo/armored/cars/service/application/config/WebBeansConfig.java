package com.jcolaiacovo.armored.cars.service.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.jcolaiacovo.armored.cars"})
public class WebBeansConfig extends WebMvcConfigurerAdapter {

    private ObjectMapper jacksonMapper;

    @Autowired
    public WebBeansConfig(ObjectMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(this.jacksonMapper);
        converters.add(converter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("web/assets/**").addResourceLocations("/components/", "/config/", "/app/assets/styles/",
                "/app/assets/images/", "/dist/", "/app/views/");
    }

}