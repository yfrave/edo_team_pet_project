package com.education.service.question.impl;

import com.education.model.dto.QuestionDto;
import com.education.service.question.QuestionService;
import com.netflix.appinfo.InstanceInfo;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Класс QuestionServiceImpl.
 * Реализует QuestionService.
 * Сервис класс для бизнес-логики запросов на edo-repository
 */
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private RestTemplate rest;
    private InstanceInfo instance;
    private final String BASE_URL = "/api/repository/question/";
    private final String HTTP = "http";

    /**
     * Метод для сохранения объекта Question в БД.
     * @param question
     */
    @Override
    public void save(QuestionDto question) {
        String path = "add";
        var uri = getUri(path);
        rest.postForObject(uri, question, QuestionDto.class);
    }

    /**
     * Метод для архивирования обращения Question
     * @param id
     */
    @Override
    public void moveToArchive(Long id) {
        var uri = getUri(Long.toString(id));
        rest.put(uri, null);
    }

    /**
     * Метод для поиска обращения Question по id.
     * @param id
     * @return QuestionDto
     */
    @Override
    public QuestionDto findById(Long id) {
        var uri = getUri(Long.toString(id));
        try {
            return rest.getForObject(uri, QuestionDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод поиска списка обращений Question по списку id.
     * @param ids
     * @return List<QuestionDto>
     */
    @Override
    public List<QuestionDto> findAllById(List<Long> ids) {
        String path = "all";
        return getQuestionDtos(ids, path);
    }

    /**
     * Метод для поиска неархивированного обращения Question по id.
     * @param id
     * @return QuestionDto
     */
    @Override
    public QuestionDto findByIdNotArchived(Long id) {
        String path = "notArchived/" + id;
        var uri = getUri(path);
        try {
            return rest.getForObject(uri, QuestionDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод для поиска неархивированных обращений Question по списку их id.
     * @param ids
     * @return List<QuestionDto>
     */
    @Override
    public List<QuestionDto> findAllByIdNotArchived(List<Long> ids) {
        String path = "notArchivedAll";
        return getQuestionDtos(ids, path);

    }

    /**
     * Метод получения списка обращений
     * (используется в методах findAllById и findAllByIdNotArchived).
     * @param ids
     * @param path
     * @return List<QuestionDto>
     */
    private List<QuestionDto> getQuestionDtos(List<Long> ids, String path) {
        var uri = getUri(path);
        var request = new RequestEntity<>(ids, HttpMethod.POST, uri);
        try {
            var response
                    = rest.exchange(request, new ParameterizedTypeReference<List<QuestionDto>>() {
            });
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод для получения URI.
     * @param path
     * @return URI
     */
    private URI getUri(String path) {
        return UriComponentsBuilder
                .fromPath(BASE_URL + path)
                .scheme(HTTP)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(path)
                .toUri();
    }
}
