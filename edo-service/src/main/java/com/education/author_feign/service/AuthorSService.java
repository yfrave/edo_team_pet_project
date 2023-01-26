package com.education.author_feign.service;

import com.education.model.dto.AuthorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Интерфейс методов работы с БД для автора
 * Сервис
 * Модуль edo-service
 */
public interface AuthorSService {
    ResponseEntity<AuthorDto> save(AuthorDto author);
    ResponseEntity<String> delete(Long id);
    AuthorDto findById(Long id);
    List<AuthorDto> findAllById(List<String> id);
}
