package com.jcolaiacovo.armored.cars.service.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Julian on 26/12/2016.
 */
@Configuration
public class JacksonMapperConfiguration {

    @Bean
    public ObjectMapper jacksonMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return om;
    }

}
