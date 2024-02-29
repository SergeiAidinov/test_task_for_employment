package ru.yandex.incoming34.test_task_for_employment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

@Configuration
public class Config {

    @Value("${app.supportedlanguages}")
    private List<String> langList;
    @Value("${app.appid}")
    private String appId;
    @Value("${app.apihttp}")
    private String apiHttp;
    @Value("${app.dummyurl}")
    private String dummyUrl;
    @Value("${app.nodes}")
    private List<String> nodeList;


    @Bean
    public List<String> supportedlanguages(){

        return langList;
    }

    @Bean
    public List<String> nodeList(){
        return nodeList;
    }

    @Bean
    public Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("appId", appId);
        properties.setProperty("apiHttp", apiHttp);
        properties.setProperty("dummyUrl", dummyUrl);
        return properties;
    }

}
