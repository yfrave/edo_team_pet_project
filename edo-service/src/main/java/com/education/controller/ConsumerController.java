package com.education.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/sayhello")
    public String letsSayHello() {
        Application application = eurekaClient.getApplication("EDO-REPOSITORY");
        InstanceInfo instanceInfo = application.getInstances().get(0);
//      String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost" + ":" + port + "/api/repository/theme/1";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        return response.getBody();
    }
}