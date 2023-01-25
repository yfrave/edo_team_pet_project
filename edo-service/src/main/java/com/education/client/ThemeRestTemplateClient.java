package com.education.client;

import com.education.model.dto.ThemeDto;
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

import java.util.List;

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

    public List<ThemeDto> findAllById(List<Long> ids) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(baseUrl)
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .queryParam("ids", ids)
                        .build()
                        .toUri());

        var response = restTemplate.exchange(request, new ParameterizedTypeReference<List<ThemeDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the themes");
        }
        return response.getBody();
    }

    public ThemeDto findByIdNotArchived(Long id) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(baseUrl + "/notArchived/{id}")
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

    /**
     * Отправляет запрос для предоставления списка не заархивированных ThemeDto номенклатур по id
     * принимает список id искомых номенклатуру
     */
    public List<ThemeDto> findAllByIdNotArchived(List<Long> ids) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(baseUrl + "/notArchived")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .queryParam("ids", ids)
                        .build()
                        .toUri());

        var response = restTemplate.exchange(request, new ParameterizedTypeReference<List<ThemeDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the themes");
        }
        return response.getBody();
    }


    public Long moveToArchive(Long id) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.DELETE,
                UriComponentsBuilder.fromPath(baseUrl + "/{id}")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = restTemplate.exchange(request, Long.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't move to archive");
        }
        return response.getBody();
    }
}