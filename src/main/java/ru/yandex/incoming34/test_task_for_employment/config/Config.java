package ru.yandex.incoming34.test_task_for_employment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {

    @Value("${supportedlanguages}")
    private List<String> langList;

    @Bean
    public List<String> supportedlanguages(){
        return langList;
    }
}
