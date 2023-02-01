package com.education.service.appeal.impl;

import com.education.model.dto.AppealDto;
import com.education.service.appeal.AppealService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;

import org.apache.http.HttpHost;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;

    private final String BASE_URL = "/api/repository/appeal";

    private final String SERVICE_NAME = "edo-repository";


    private InstanceInfo getInstance() {
        Application application = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = application.getInstances().get(new Random().nextInt(1));
        return instance;
    }

    private URI getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        URI uri = UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .buildAndExpand(pathVariable)
                .toUri();
        return uri;
    }

    @Override
    public void save(AppealDto appeal) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, "");
        TEMPLATE.postForObject(uri, appeal, AppealDto.class);
    }

    @Override
    public void moveToArchive(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/toArchive/%s", id.toString()));
        TEMPLATE.put(uri, null);
    }

    @Override
    public AppealDto findById(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/byId/%s", id.toString()));
        AppealDto response = TEMPLATE.getForObject(uri, AppealDto.class);
        return response;
    }

    @Override
    public List<AppealDto> findAllById(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        URI uri = getURIByInstance(instanceInfo, String.format("/allById/%s", path));
        AppealDto[] response = TEMPLATE.getForObject(uri, AppealDto[].class);
        return Arrays.asList(response);
    }

    @Override
    public AppealDto findByIdNotArchived(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/notArchived/%s", id.toString()));
        AppealDto response = TEMPLATE.getForObject(uri, AppealDto.class);
        return response;
    }

    @Override
    public List<AppealDto> findAllByIdNotArchived(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        URI uri = getURIByInstance(instanceInfo, String.format("/allNotArchived/%s", path));
        AppealDto[] response = TEMPLATE.getForObject(uri, AppealDto[].class);
        return Arrays.asList(response);
    }
}
