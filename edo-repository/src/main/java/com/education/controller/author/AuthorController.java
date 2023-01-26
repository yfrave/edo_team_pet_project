package com.education.controller.author;

import com.education.entity.Author;
import com.education.model.dto.AuthorDto;
import com.education.service.author.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/repository/author")
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-repository)")
public class AuthorController {
    //едо рест нужно
    //т.к. сервисы модуля edo-service не открывают транзакции в бд, продумать план отката в случае получения ошибки.

    private final AuthorService authorService;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public AuthorDto showById(@PathVariable("id") Long id) {
        return Author.authorToDto(authorService.findById(id));
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public List<AuthorDto> showAllById(@RequestBody List<Long> ids) {
        return authorService.findAllById(ids);
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto) {
        authorService.save(authorDto);
        return authorDto != null ?
                ResponseEntity.ok(authorDto)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление сущности")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity.ok("DELETED");
    }

}
