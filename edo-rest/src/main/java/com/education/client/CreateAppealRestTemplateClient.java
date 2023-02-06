package com.education.client;

import com.education.model.dto.AppealDto;
import com.education.model.dto.NomenclatureDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CreateAppealRestTemplateClient {


    private final RestTemplate restTemplate;

    private final EurekaClient eurekaClient;

    static final String BASE_URL = "api/repository/appeal";

    static final String SERVICE_NAME = "edo-repository";

    static final String SCHEMA_NAME = "http";


    public AppealDto save(AppealDto appealDto) {
        appealDto.setCreationDate(ZonedDateTime.now());
        var request = new RequestEntity(appealDto, HttpMethod.POST,
                buildUri(getInstance(), "/"));

        var response = restTemplate.exchange(request, AppealDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save nomenclature");
        }

        return response.getBody();
    }

    private InstanceInfo getInstance() {
        Random random = new Random();
        Application app = eurekaClient.getApplication(SERVICE_NAME);
        List<InstanceInfo> instances = app.getInstances();
        return instances.get(random.nextInt(instances.size()));
    }

    /**
     * Build URI
     * @param instance InstanceInfo
     * @param path This is the request path
     * @return URI
     */
    private URI buildUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEMA_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }
}