package com.education.author_feign.service.impl;

import com.education.author_feign.service.AuthorSService;
import com.education.model.dto.AuthorDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

/**
 * Имплементация интерфейса методов для работы в БД с сущностью Автора
 * Сервис
 * Модуль edo-service
 */
@Service
@AllArgsConstructor
public class AuthorSServiceImpl implements AuthorSService {

    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;

    /**
     * Сохранение сущности
     */
    @Override
    public ResponseEntity<AuthorDto> save(AuthorDto author) {
        Application application = eurekaClient.getApplication("edo-repository");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/repository/author";
        return restTemplate.postForEntity(url, author, AuthorDto.class);

    }

    /**
     * Удаление сущности
     */
    @Override
    public ResponseEntity<String> delete(Long id) {
        Application application = eurekaClient.getApplication("edo-repository");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/repository/author/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("DELETED");
    }

    /**
     * Поиск сущности по id
     */
    @Override
    public AuthorDto findById(Long id) {
        Application application = eurekaClient.getApplication("edo-repository");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/repository/author/" + id;
        return restTemplate.getForEntity(url, AuthorDto.class).getBody();
    }

    /**
     * Поиск сущностей по значениям их id
     */
    @Override
    public List<AuthorDto> findAllById(List<String> ids) {
        Application application = eurekaClient.getApplication("edo-repository");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/repository/author/findAll";

        return restTemplate.exchange(new RequestEntity<>(ids, HttpMethod.POST, URI.create(url)), new ParameterizedTypeReference<List<AuthorDto>>() {
        }).getBody();
    }
}
