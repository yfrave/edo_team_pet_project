package com.education.service.Address.impl;

import com.education.model.dto.AddressDto;
import com.education.service.Address.AddressService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressRestTemplateServiceImpl implements AddressService {
    private final String URL = "http://edo-repository/api/repository/address/";

    /**
     * Клиент для отправки и получения запросов
     */
    private final RestTemplate template;

    /**
     * Клиент для получения instance
     */
    private final EurekaClient eurekaClient;

    /**
     * путь до рест контроллера address
     */
    private final String baseUrl = "/api/repository/address";

    /**
     * Имя модуля в который отправляются запросы
     */
    private final String serviceName = "edo-repository";


    @Override
    public AddressDto getById(Long id) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();
        URI uri = UriComponentsBuilder.fromPath(baseUrl + "/{id}")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
        var request = new RequestEntity<>(null, HttpMethod.GET, uri);
        return template.exchange(request, AddressDto.class).getBody();
    }

    @Override
    public List<AddressDto> fetchAddressedList(List<Long> idList) {
        var app = eurekaClient.getApplication(serviceName);
        var instance = app.getInstances().stream().findAny().get();
        var uri = UriComponentsBuilder.fromPath(baseUrl + "/findAll")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
        var request = new RequestEntity<>(idList, HttpMethod.GET, uri);
        var myBean = new ParameterizedTypeReference<List<AddressDto>>() {};
        var response = template.exchange(request, List.class);
//        return response.getBody();
        return new ArrayList<>();
    }

    @Override
    public AddressDto save(AddressDto address) {
        var app = eurekaClient.getApplication(serviceName);
        var instance = app.getInstances().stream().findAny().get();
        var uri = UriComponentsBuilder.fromPath(baseUrl + "/")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
        var request = new RequestEntity<>(address, HttpMethod.POST, uri);
        return template.exchange(request, AddressDto.class).getBody();
    }

    @Override
    public void delete(Long id) {
        var app = eurekaClient.getApplication(serviceName);
        var instance = app.getInstances().stream().findAny().get();
        var uri = UriComponentsBuilder.fromPath(baseUrl + "/{id}")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
//        var request = new RequestEntity<>(null, HttpMethod.DELETE, uri);
        template.delete(uri);
    }
}
