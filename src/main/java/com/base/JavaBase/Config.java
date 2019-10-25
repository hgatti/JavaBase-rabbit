package com.base.JavaBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    static ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper;
    }
}
