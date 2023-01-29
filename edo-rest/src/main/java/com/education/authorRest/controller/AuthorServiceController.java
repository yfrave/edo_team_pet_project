package com.education.authorRest.controller;

import com.education.model.dto.AuthorDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/rest/author")
@AllArgsConstructor
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-rest)")
public class AuthorServiceController {
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public AuthorDto showById(@PathVariable("id") Long id) {
        Application application = eurekaClient.getApplication("edo-service");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/service/author/" + id;

        return restTemplate.getForEntity(url, AuthorDto.class).getBody();
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public ResponseEntity<List<AuthorDto>> showAllById(@RequestBody List<String> ids) {
        Application application = eurekaClient.getApplication("edo-service");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/service/author/findAll";

        return restTemplate.exchange(new RequestEntity<>(ids, HttpMethod.POST, URI.create(url)), new ParameterizedTypeReference<List<AuthorDto>>() {
        });
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        Application application = eurekaClient.getApplication("edo-service");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/service/author";

        return author != null ?
                restTemplate.postForEntity(url, author, AuthorDto.class)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление сущности")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        Application application = eurekaClient.getApplication("edo-service");
        InstanceInfo instanceInfo = application.getInstances()
                .get((int) (application.getInstances().size() * Math.random()));
        int port = instanceInfo.getPort();

        String url = "http://localhost" + ":" + port + "/api/service/author/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("DELETED");
    }
}
