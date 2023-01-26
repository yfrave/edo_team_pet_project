package com.education.service.resolution.impl;

import com.education.model.dto.ResolutionDto;
import com.education.service.resolution.ResolutionService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private final RestTemplate template;

    private final EurekaClient eurekaClient;

    private final String baseUrl = "/api/repository/resolution";

    private final String serviceName = "edo-repository";


    private InstanceInfo getInstance() {
        Application application = eurekaClient.getApplication(serviceName);
        InstanceInfo instance = application.getInstances().get(new Random().nextInt(application.size()));
        return instance;
    }

    private URI getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        URI uri = UriComponentsBuilder.fromPath(baseUrl + pathVariable)
                .scheme("http")
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .buildAndExpand(pathVariable)
                .toUri();
        return uri;
    }

    @Override
    public boolean save(ResolutionDto resolution) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, "");
        System.out.println(resolution.toString());
        template.postForObject(uri, resolution, ResolutionDto.class);
        if (findById(resolution.getId()) == resolution) {
            return true;
        }
        return false;
    }

    @Override
    public void moveToArchive(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/ToArchive/%s", id.toString()));
        template.put(uri, null);
    }

    @Override
    public ResolutionDto findById(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/ById/%s", id.toString()));
        ResolutionDto response = template.getForObject(uri, ResolutionDto.class);
        return response;
    }

    @Override
    public List<ResolutionDto> findAllById(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        URI uri = getURIByInstance(instanceInfo, String.format("/AllById/%s", path));
        ResolutionDto[] response = template.getForObject(uri, ResolutionDto[].class);
        return Arrays.asList(response);
    }

    @Override
    public ResolutionDto findByIdNotArchived(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/NotArchived/%s", id.toString()));
        ResolutionDto response = template.getForObject(uri, ResolutionDto.class);
        return response;
    }

    @Override
    public List<ResolutionDto> findAllByIdNotArchived(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        URI uri = getURIByInstance(instanceInfo, String.format("/AllNotArchived/%s", path));
        ResolutionDto[] response = template.getForObject(uri, ResolutionDto[].class);
        return Arrays.asList(response);
    }
}
