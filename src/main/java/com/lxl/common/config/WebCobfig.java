package com.lxl.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxl.common.util.convert.CustomDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.DateFormat;

public class WebCobfig {

    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {

        ObjectMapper objectMapper = jackson2ObjectMapperBuilder.build();

        DateFormat dateFormat = objectMapper.getDateFormat();
        objectMapper.setDateFormat(new CustomDataFormat(dateFormat));

        MappingJackson2HttpMessageConverter mappingJsonHttpMessageConverter = new MappingJackson2HttpMessageConverter(
                objectMapper);
        return mappingJsonHttpMessageConverter;
    }
}
