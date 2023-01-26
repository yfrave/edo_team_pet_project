package com.education.client;

import com.education.model.dto.ThemeDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Level;

@Component
@Log
@RequiredArgsConstructor
public class ThemeRestTemplateClient {

    /**
     * Клиент для отправки и получения запросов
     */
    private final RestTemplate REST_TEMPLATE;

    /**
     * Клиент для получения instance
     */
    private final EurekaClient EUREKA_CLIENT;

    /**
     * путь рест котроллера номенклатуры
     */
    private final String BASE_URL = "api/repository/theme";

    /**
     * Имя модуля в который отправляются запросы
     */
    private final String SERVICE_NAME = "edo-repository";

    private String SCHEME = "http";

    public ThemeDto save(ThemeDto themeDto) {
        Application app = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = app.getInstances().get((int) (app.getInstances().size() * Math.random()));
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(themeDto, HttpMethod.POST,
                UriComponentsBuilder.fromPath(BASE_URL + "/")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .build()
                        .toUri());

        var response = REST_TEMPLATE.exchange(request, ThemeDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save the theme");
        }
        return response.getBody();
    }

    public ThemeDto findById(Long id) {
        Application app = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = app.getInstances().get((int) (app.getInstances().size() * Math.random()));
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());


        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(BASE_URL + "/{id}")
                        .scheme(SCHEME)
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = REST_TEMPLATE.exchange(request, ThemeDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the theme");
        }

        return response.getBody();
    }

    public List<ThemeDto> findAllById(List<Long> ids) {
        Application app = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = app.getInstances().get((int) (app.getInstances().size() * Math.random()));
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());


        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(BASE_URL)
                        .scheme(SCHEME)
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .queryParam("ids", ids)
                        .build()
                        .toUri());

        var response = REST_TEMPLATE.exchange(request, new ParameterizedTypeReference<List<ThemeDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the themes");
        }
        return response.getBody();
    }

    public ThemeDto findByIdNotArchived(Long id) {
        Application app = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = app.getInstances().get((int) (app.getInstances().size() * Math.random()));
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());


        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(BASE_URL + "/notArchived/{id}")
                        .scheme(SCHEME)
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = REST_TEMPLATE.exchange(request, ThemeDto.class);

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
        Application app = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = app.getInstances().get((int) (app.getInstances().size() * Math.random()));
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());


        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(BASE_URL + "/notArchived")
                        .scheme(SCHEME)
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .queryParam("ids", ids)
                        .build()
                        .toUri());

        var response = REST_TEMPLATE.exchange(request, new ParameterizedTypeReference<List<ThemeDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the themes");
        }
        return response.getBody();
    }


    public Long moveToArchive(Long id) {
        Application app = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = app.getInstances().get((int) (app.getInstances().size() * Math.random()));
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());



        var request = new RequestEntity(null, HttpMethod.DELETE,
                UriComponentsBuilder.fromPath(BASE_URL + "/{id}")
                        .scheme(SCHEME)
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = REST_TEMPLATE.exchange(request, Long.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't move to archive");
        }
        return response.getBody();
    }
}