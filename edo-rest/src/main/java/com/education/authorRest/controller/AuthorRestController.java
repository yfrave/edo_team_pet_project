package com.education.authorRest.controller;

import com.education.model.dto.AuthorDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/rest/author")
@AllArgsConstructor
@Log4j2
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-rest)")
public class AuthorRestController {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "/api/service/author/";
    private final String HTTP = "http";
    private EurekaClient client;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public AuthorDto showById(@PathVariable("id") Long id) {
        log.info("отправил AuthorDto.class");
        var uri = getUri(id.toString());
        return restTemplate.getForEntity(uri, AuthorDto.class).getBody();
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public List<AuthorDto> showAllById(@RequestBody List<String> ids) {
        var uri = getUri("findAll");
        log.info("отправил list AuthorDto.class");
        return restTemplate.exchange(new RequestEntity<>(ids, HttpMethod.POST,uri), new ParameterizedTypeReference<List<AuthorDto>>() {
        }).getBody();
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        var uri = getUri("");
        log.info("сохранил AuthorDto.class");
        return author != null ?
                restTemplate.postForEntity(uri, author, AuthorDto.class)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление сущности")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        var uri = getUri(id.toString());
        restTemplate.delete(uri);
        log.info("удалил AuthorDto.class");
        return ResponseEntity.ok("DELETED");
    }

    /**
     * Метод для получения URI.
     * @param path
     * @return URI
     */
    private URI getUri(String path) {
        InstanceInfo instance = getInstance();
        return UriComponentsBuilder
                .fromPath(BASE_URL + path)
                .scheme(HTTP)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(path)
                .toUri();
    }

    /**
     * Метод для получения Instance
     * @return InstanceInfo
     */
    public InstanceInfo getInstance() {
        String SERVICE_NAME = "edo-service";
        List<InstanceInfo> instances
                = client.getApplication(SERVICE_NAME).getInstances();

        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));

        log.info(instance.getPort());

        return instance;
    }
}
