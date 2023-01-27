package com.education.service.impl;

import com.education.model.dto.FilePoolDto;
import com.education.service.FilePoolService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log
public class FilePoolServiceImpl implements FilePoolService {
    @NotNull
    private RestTemplate restTemplate;

    @NotNull
    private EurekaClient eurekaClient;

    private String baseUrl = "/api/repository/file_pool/";
    private String uriScheme = "http";


    /**
     * findById in db
     *
     * @param id Long
     * @return FilePoolDto
     */
    public FilePoolDto findById(Long id) {
        URI uri = generateUri(this.getInstance(), id);
        RequestEntity<Object> request = new RequestEntity<>(null, HttpMethod.GET, uri);
        return restTemplate.exchange(request, FilePoolDto.class).getBody();
    }

    /**
     * Add in db method
     *
     * @param filePoolDto FilePoolDto
     * @return FilePoolDto
     */
    public FilePoolDto add(FilePoolDto filePoolDto) {
        URI uri = generateUri(this.getInstance(), "/");
        RequestEntity<Object> request = new RequestEntity(filePoolDto, HttpMethod.POST, uri);
        return restTemplate.exchange(request, FilePoolDto.class).getBody();

    }

    /**
     * findAllById in db
     *
     * @param ids List<Long>
     * @return List<FilePoolDto>
     */
    public List<FilePoolDto> findAllById(List<Long> ids) {
        URI uri = generateUri(this.getInstance(), "/findAll");
        var request = new RequestEntity<>(ids, HttpMethod.POST, uri);
        var listOfFilePoolDtoType = new ParameterizedTypeReference<List<FilePoolDto>>() {
        };

        return restTemplate
                .exchange(request, listOfFilePoolDtoType)
                .getBody();
    }

    /**
     * Move to archive in db
     *
     * @param id Long
     */
    public void moveToArchive(Long id) {
        String lastPathName = "/" + id;
        URI uri = generateUri(this.getInstance(), lastPathName);
        var request = new RequestEntity<>(HttpMethod.PUT, uri);
        restTemplate.exchange(request, Void.class);
    }

    /**
     * getInstance
     *
     * @return InstanceInfo
     */
    private InstanceInfo getInstance() {
        String serviceName = "edo-repository";
        Application app = eurekaClient.getApplication(serviceName);
        if (app == null) {
            log.warning("EurekaClient  не смогла достучаться до edo-repository");
        }
        return app.getInstances().get(new Random().nextInt(app.size()));
    }

    /**
     * generateUri
     *
     * @param instance InstanceInfo
     * @param id       Long
     * @return URI
     */
    private URI generateUri(InstanceInfo instance, Long id) {
        String lastPartComponent = "/{id}";
        return UriComponentsBuilder.fromPath(baseUrl + lastPartComponent)
                .scheme(uriScheme)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
    }

    /**
     * generateUri
     *
     * @param instance InstanceInfo
     * @param path     String
     * @return URI
     */
    private URI generateUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(baseUrl + path)
                .scheme(uriScheme)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }

}
