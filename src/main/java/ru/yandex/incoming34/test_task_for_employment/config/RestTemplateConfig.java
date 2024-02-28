package ru.yandex.incoming34.test_task_for_employment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate dummyRestTemplate(){
        return new RestTemplate();
    }
}
