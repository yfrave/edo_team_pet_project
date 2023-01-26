package com.education.author_feign.controller;

import com.education.author_feign.service.AuthorSService;
import com.education.model.dto.AuthorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/service/author")
@AllArgsConstructor
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-service)")
public class AuthorServiceController {
    private final AuthorSService authorSService;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public AuthorDto showById(@PathVariable("id") Long id) {
        return authorSService.findById(id);
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public ResponseEntity<List<AuthorDto>> showAllById(@RequestBody List<String> ids) {
        return ResponseEntity.ok(authorSService.findAllById(ids));
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        authorSService.save(author);
        return author != null ?
                ResponseEntity.ok(author)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление сущности")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        authorSService.delete(id);
        return ResponseEntity.ok("DELETED");
    }
}
