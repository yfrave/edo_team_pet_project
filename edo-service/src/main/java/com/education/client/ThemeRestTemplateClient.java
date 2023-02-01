package com.education.client;

import com.education.model.dto.ThemeDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
        InstanceInfo instance = getInstance();
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(themeDto, HttpMethod.POST,
                UriComponentsBuilder.fromPath(BASE_URL + "/")
                        .scheme(SCHEME)
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
        InstanceInfo instance = getInstance();
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
        InstanceInfo instance = getInstance();
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
        InstanceInfo instance = getInstance();
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
        InstanceInfo instance = getInstance();
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
        InstanceInfo instance = getInstance();
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

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    /**
     * Build URI
     *
     * @param instance InstanceInfo
     * @param path     This is the request path
     * @return URI
     */
    private URI buildUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }

    /**
     * Build uri with id
     *
     * @param instance InstanceInfo
     * @param path     This is the request path
     * @param id       Long
     * @return URI
     */
    private URI buildUri(InstanceInfo instance, String path, Long id) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
    }


}