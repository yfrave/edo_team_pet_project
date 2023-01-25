package com.education.service.Address.impl;

import com.education.model.dto.AddressDto;
import com.education.service.Address.AddressService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Сервис для контроллера, работает с AddressDto
 */
@Service
@AllArgsConstructor
public class AddressRestTemplateServiceImpl implements AddressService {

    /**
     * Клиент для отправки и получения запросов
     */
    private final RestTemplate template;

    /**
     * Клиент для получения instance
     */
    private final EurekaClient eurekaClient;

    /**
     * путь до рест контроллера репозитория
     */
    private final String baseUrl = "/api/repository/address";

    /**
     * Имя модуля, в который отправляются запросы
     */
    private final String serviceName = "edo-repository";


    /**
     * get addresses By Id
     * @param id Long
     * @return AddressDto
     */
    @Override
    public AddressDto getById(Long id) {
        InstanceInfo instance = eurekaClient.getApplication(serviceName)
                .getInstances()
                .stream()
                .findAny()
                .orElseThrow();
        URI uri = UriComponentsBuilder.fromPath(baseUrl + "/{id}")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
        var request = new RequestEntity<>(null, HttpMethod.GET, uri);

        return template.exchange(request, AddressDto.class).getBody();
    }

    /**
     * fetch Addressed by List
     * @param idList List<Long>
     * @return List<AddressDto>
     */
    @Override
    public List<AddressDto> fetchAddressedList(List<Long> idList) {
        InstanceInfo instance = eurekaClient.getApplication(serviceName)
                .getInstances()
                .stream()
                .findAny()
                .orElseThrow();
        var uri = UriComponentsBuilder.fromPath(baseUrl + "/findAll")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
        var request = new RequestEntity<>(idList, HttpMethod.POST, uri);
        var myBean = new ParameterizedTypeReference<List<AddressDto>>() {
        };
        var response = template.exchange(request, myBean);

        return response.getBody();
    }

    /**
     * save Address in db
     * @param address AddressDto
     * @return AddressDto
     */
    @Override
    public AddressDto save(AddressDto address) {
        InstanceInfo instance = eurekaClient.getApplication(serviceName)
                .getInstances()
                .stream()
                .findAny()
                .orElseThrow();
        var uri = UriComponentsBuilder.fromPath(baseUrl + "/")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
        var request = new RequestEntity<>(address, HttpMethod.POST, uri);

        return template.exchange(request, AddressDto.class).getBody();
    }

    /**
     * delete address from db
     * @param id Long
     */
    @Override
    public void delete(Long id) {
        InstanceInfo instance = eurekaClient.getApplication(serviceName)
                .getInstances()
                .stream()
                .findAny()
                .orElseThrow();
        var uri = UriComponentsBuilder.fromPath(baseUrl + "/{id}")
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
        template.delete(uri);
    }
}
