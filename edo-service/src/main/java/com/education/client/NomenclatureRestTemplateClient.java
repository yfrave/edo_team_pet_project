package com.education.client;

import com.education.model.dto.NomenclatureDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NomenclatureRestTemplateClient {
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;
    private final EurekaClient eurekaClient;

    private final String baseUrl = "api/repository/nomenclature";

    private final String serviceName = "edo-repository";

    public NomenclatureDto save(NomenclatureDto nomenclature) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(nomenclature, HttpMethod.POST,
                UriComponentsBuilder.fromPath(baseUrl + "/")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .build()
                        .toUri());

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save nomenclature");
        }

        return response.getBody();
    }

    public NomenclatureDto findById(Long id) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(baseUrl + "/{id}")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclature");
        }

        return response.getBody();
    }

    public List<NomenclatureDto> findAllById(List<Long> ids) {
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

        var response = restTemplate.exchange(request, new ParameterizedTypeReference<List<NomenclatureDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclatures");
        }

        return response.getBody();
    }

    public NomenclatureDto findByIdNotArchived(Long id) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.GET,
                UriComponentsBuilder.fromPath(baseUrl + "/notArchived/{id}")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclature");
        }

        return response.getBody();
    }

    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> ids) {
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

        var response = restTemplate.exchange(request, new ParameterizedTypeReference<List<NomenclatureDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclatures");
        }

        return response.getBody();
    }

    public NomenclatureDto moveToArchive(Long id) {
        Application app = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = app.getInstances().stream().findAny().get();

        var request = new RequestEntity(null, HttpMethod.PATCH,
                UriComponentsBuilder.fromPath(baseUrl + "/archived/{id}")
                        .scheme("http")
                        .host(instance.getHostName())
                        .port(instance.getPort())
                        .buildAndExpand(id)
                        .toUri());

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't move to archive");
        }

        return response.getBody();
    }
}
