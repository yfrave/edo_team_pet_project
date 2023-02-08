package com.education.client;

import com.education.model.dto.AppealDto;
import com.education.model.dto.AuthorDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FilePoolDto;
import com.education.model.dto.QuestionDto;
import com.education.model.dto.ResolutionDto;
import com.education.model.dto.ThemeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateAppealRestTemplateClient {

    private final XmlMapper objectMapper = new XmlMapper();
    private final RestTemplate restTemplate;

    private final EurekaClient eurekaClient;

    static final String BASE_URL = "api/service/";

    static final String SERVICE_NAME = "edo-service";

    static final String SCHEMA_NAME = "http";

    public AppealDto saveAppeal(AppealDto appealDto) throws JsonProcessingException {
        return objectMapper.readValue(save(appealDto, "appeal"), AppealDto.class);
    }

    //На момент создания ее еще нет, нужно будет перепроверить после МРа
    public List<AuthorDto> saveAuthors(List<AuthorDto> authorDtos) {
        return authorDtos
                .stream()
                .map(authorDto -> {
                    try {
                        return objectMapper.readValue(save(authorDto, "author"), AuthorDto.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    //На момент создания ее еще нет, нужно будет перепроверить после МРа
    public List<FilePoolDto> saveFilePool(List<FilePoolDto> filePoolDtos) {
        return filePoolDtos
                .stream()
                .map(filePoolDto -> {
                    try {
                        return objectMapper.readValue(save(filePoolDto,"filePool"), FilePoolDto.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<QuestionDto> saveQuestion(List<QuestionDto> questionDtos) {
        objectMapper.registerModule(new JavaTimeModule());
        return questionDtos
                .stream()
                .map(questionDto -> {
                    try {
                        return objectMapper.readValue(save(questionDto, "question/"), QuestionDto.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public ThemeDto saveTheme(ThemeDto themeDto) throws JsonProcessingException {
        return objectMapper.readValue(save(themeDto, "theme/"), ThemeDto.class);
    }

    //Нужен или нет будет зависеть от того как мы будем сохранять Resolution
    public ResolutionDto saveResolution(ResolutionDto resolutionDto) throws JsonProcessingException {
        return objectMapper.readValue(save(resolutionDto, "resolution"), ResolutionDto.class);
    }

    //Нужен или нет будет зависеть от того как мы будем сохранять Resolution
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws JsonProcessingException {
        return objectMapper.readValue(save(employeeDto, "employee"), EmployeeDto.class);
    }

    private String save(Object dto, String entityName) {
        var request = new RequestEntity<>(dto, HttpMethod.POST,
                buildUri(getInstance(), entityName));

        var response = restTemplate.exchange(request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save" + entityName.replace("/", ""));
        }
        return response.getBody();
    }

    private InstanceInfo getInstance() {
        Random random = new Random();
        Application app = eurekaClient.getApplication(SERVICE_NAME);
        List<InstanceInfo> instances = app.getInstances();
        return instances.get(random.nextInt(instances.size()));
    }

    /**
     * Build URI
     * @param instance InstanceInfo
     * @param path This is the request path
     * @return URI
     */
    private URI buildUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEMA_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }
}