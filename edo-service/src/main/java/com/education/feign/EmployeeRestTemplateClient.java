package com.education.feign;


import com.education.model.dto.EmployeeDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Ритман Степан
 * Класс для отправк запросов в edo-repositotry
 */
@Component
@RequiredArgsConstructor
public class EmployeeRestTemplateClient {
    /**
     * Объект класса EurekaClient
     */
    private final EurekaClient eurekaClient;
    /**
     * Объект класса RestTemplate
     */
    private final RestTemplate restTemplate;
    /**
     * Базовый URL для API edo-service
     */
    private final String BASIC_URL = "api/repository/employee";

    /**
     * Метод, возврашающий EmployeeDto по заданному id
     * @param id id сущности
     * @param notArchivedOnly Логическая переменная. При значении true поиск выполняется
     *                        только среди сущнотей, не находящихся в архиве.
     *                        При значении false - среди всех сущностей
     * @return объект класса EmployeeDto, соответствующий сущности с указанным id.
     * В случае, если объект с заданным id не найден, метод возвращает null.
     */
    public EmployeeDto getEmployeeById(Long id, boolean notArchivedOnly) {
        InstanceInfo instanceInfo = getRandomInstance(eurekaClient);
        try {
            return restTemplate.getForObject(UriComponentsBuilder
                            .fromPath(BASIC_URL + "/{id}")
                            .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                            .host(instanceInfo.getHostName())
                            .port(instanceInfo.getPort())
                            .queryParam("notArchivedOnly", notArchivedOnly)
                            .buildAndExpand(id)
                            .toUri()
                    , EmployeeDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    /**
     * Метод, возврашающий список EmployeeDto по заданным id
     * @param ids id сущностей
     * @param notArchivedOnly Логическая переменная. При значении true поиск выполняется
     *                        только среди сущнотей, не находящихся в архиве.
     *                        При значении false - среди всех сущностей
     * @return список EmployeeDto, соответствующих сущностям с указанными id.
     * В случае, если объекты с заданным id не найдены, метод возвращает пустой список.
     */
    public List<EmployeeDto> getAllEmployeeById(List<Long> ids, boolean notArchivedOnly) {
        InstanceInfo instanceInfo = getRandomInstance(eurekaClient);
        try {
            return Arrays.asList(Objects.requireNonNull(
                    restTemplate.getForObject(UriComponentsBuilder
                                    .fromPath(BASIC_URL)
                                    .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                                    .host(instanceInfo.getHostName())
                                    .port(instanceInfo.getPort())
                                    .queryParam("ids", ids)
                                    .queryParam("notArchivedOnly", notArchivedOnly)
                                    .build()
                                    .toUri()
                            , EmployeeDto[].class)));
        } catch (HttpClientErrorException.NotFound e) {
            return new ArrayList<>();
        }
    }

    /**
     * Метод, сохраняющий сущность Employee в таблицу
     * @param employee сохраняемая сущность
     * @return объект каласса EmployeeDto, соответствующий сохраненной сущности
     */
    public EmployeeDto saveEmployee(EmployeeDto employee) {
        InstanceInfo instanceInfo = getRandomInstance(eurekaClient);
        return restTemplate.postForObject(UriComponentsBuilder
                .fromPath(BASIC_URL).scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .build()
                .toUri()
                , employee
                , EmployeeDto.class);
    }

    /**
     * Добавляет сущность из таблицы employee в архив
     * @param id id сущности
     */
    public void moveEmployeeToArchive(Long id) {
        InstanceInfo instanceInfo = getRandomInstance(eurekaClient);
        restTemplate.exchange(UriComponentsBuilder
                        .fromPath(BASIC_URL + "/{id}")
                        .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                        .host(instanceInfo.getHostName())
                        .port(instanceInfo.getPort())
                .buildAndExpand(id).toUri()
                , HttpMethod.DELETE, null, EmployeeDto.class);
    }

    /**
     *
     * @param eurekaClient объект класса EurekaClient
     * @return Случайный экземпляр сервиса edo-repository
     */
    private InstanceInfo getRandomInstance(EurekaClient eurekaClient) {
        var instances = eurekaClient
                .getApplication("edo-repository")
                .getInstances();
        return instances.get((int)(instances.size() * Math.random()));
    }
}
