package com.education.configuration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@AllArgsConstructor
@Log4j2
public class RestTemplateConfig {
    private  EurekaClient client;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public InstanceInfo getInstance() {
        String SERVICE_NAME = "edo-repository";
        List<InstanceInfo> instances
                = client.getApplication(SERVICE_NAME).getInstances();

        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));

        log.info(instance.getInstanceId());

        return instance;
    }

}
