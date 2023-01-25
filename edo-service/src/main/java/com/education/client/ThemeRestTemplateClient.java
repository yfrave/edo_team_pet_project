package com.education.client;

import com.education.model.dto.NomenclatureDto;
import com.education.model.dto.ThemeDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ThemeRestTemplateClient {

    /**
     * Клиент для отправки и получения запросов
     */
    private final RestTemplate restTemplate;

    /**
     * Клиент для получения instance
     */
    private final EurekaClient eurekaClient;

    /**
     * путь рест котроллера номенклатуры
     */
    private final String baseUrl = "api/repository/theme";

    /**
     * Имя модуля в который отправляются запросы
     */
    private final String serviceName = "edo-repository";

    public ThemeDto save(ThemeDto themeDto) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(themeDto, HttpMethod.POST,
                UriComponentsBuilder.fromPath(baseUrl + "/")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .build()
                        .toUri());

        var response = restTemplate.exchange(request, ThemeDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save the theme");
        }
        return response.getBody();
    }

    public ThemeDto findById(Long id) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(baseUrl + "/{id}")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = restTemplate.exchange(request, ThemeDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the theme");
        }

        return response.getBody();
    }
}