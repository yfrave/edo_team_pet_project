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

    /**
     * Сохраняет тему обращения
     */
    public ThemeDto save(ThemeDto themeDto) {
        InstanceInfo instance = getInstance();
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(themeDto, HttpMethod.POST, buildUri(instance, "/"));

        var response = REST_TEMPLATE.exchange(request, ThemeDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save the theme");
        }
        return response.getBody();
    }

    /**
     * Получает тему обращения по id
     */
    public ThemeDto findById(Long id) {
        InstanceInfo instance = getInstance();
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(null, HttpMethod.GET, buildUri(instance, "/{id}", id));

        var response = REST_TEMPLATE.exchange(request, ThemeDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the theme");
        }
        return response.getBody();
    }

    /**
     * Получает тему обращения по набору id
     */
    public List<ThemeDto> findAllById(List<Long> ids) {
        InstanceInfo instance = getInstance();
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(null, HttpMethod.GET, buildUri(instance, "", ids));

        var response = REST_TEMPLATE.exchange(request, new ParameterizedTypeReference<List<ThemeDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the themes");
        }
        return response.getBody();
    }

    /**
     * @param id
     * @return Тему обращения не перемещенную в архив
     */
    public ThemeDto findByIdNotArchived(Long id) {
        InstanceInfo instance = getInstance();
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(null, HttpMethod.GET, buildUri(instance, "/notArchived/{id}", id));

        var response = REST_TEMPLATE.exchange(request, ThemeDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the theme");
        }
        return response.getBody();
    }

    /**
     * @param ids
     * @return Список тем обращения не помещенных в архив по набору id
     */
    public List<ThemeDto> findAllByIdNotArchived(List<Long> ids) {
        InstanceInfo instance = getInstance();
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(null, HttpMethod.GET, buildUri(instance, "/notArchived", ids));

        var response = REST_TEMPLATE.exchange(request, new ParameterizedTypeReference<List<ThemeDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get the themes");
        }
        return response.getBody();
    }

    /**
     * @param id Помещает тему в архив
     */
    public Long moveToArchive(Long id) {
        InstanceInfo instance = getInstance();
        log.log(Level.INFO, "В классе ThemeRestTemplateClient используется порт {0}", instance.getPort());

        var request = new RequestEntity(null, HttpMethod.DELETE, buildUri(instance, "/{id}", id));

        var response = REST_TEMPLATE.exchange(request, Long.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't move to archive");
        }
        return response.getBody();
    }

    /**
     * Получает инстанс случайным методом
     */
    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    /**
     * строит Uri
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
     * строит Uri
     */
    private URI buildUri(InstanceInfo instance, String path, Long id) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
    }

    /**
     * строит Uri
     */
    private URI buildUri(InstanceInfo instance, String path, List<Long> ids) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .queryParam("ids", ids)
                .build()
                .toUri();
    }
}