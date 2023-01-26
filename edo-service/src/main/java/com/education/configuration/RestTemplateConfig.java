package com.education.configuration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class RestTemplateConfig {
    private  EurekaClient client;
    private final String SERVICE_NAME = "edo-repository";

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public InstanceInfo getInstance() {
        return client.getApplication(SERVICE_NAME)
                .getInstances()
                .stream()
                .findAny()
                .orElseThrow();
    }

}
