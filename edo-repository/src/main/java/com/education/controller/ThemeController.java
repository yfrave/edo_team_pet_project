package com.education.controller;

import com.education.model.dto.ThemeDto;
import com.education.service.theme.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("api/repository/theme")
@Log
@RequiredArgsConstructor
@Tag(name = "ThemeController", description = "Все методы для работы с темами обращения")
public class ThemeController {

    private final ThemeService themeService;

    @Operation(summary = "Сохраняет тему")
    @PostMapping("/")
    public ResponseEntity<ThemeDto> saveTheme(@RequestBody ThemeDto themeDto) {
        log.log(Level.INFO, "Отправляю ThemeDto на сохранение. Название темы: {0}", themeDto.getName());
        return new ResponseEntity<>(themeService.save(themeDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Выдает тему по id")
    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> getThemeById(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на выдачу ThemeDto для id = {0}", id);
        return new ResponseEntity<>(themeService.findById(id), themeService.findById(id) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Выдает тему по списку id")
    @GetMapping
    public ResponseEntity<List<ThemeDto>> getAllById(@RequestParam(value = "ids", required = false) List<Long> ids) {
        log.log(Level.INFO, "Получен запрос на выдачу ThemeDto для набора id = {0}", ids);
        return new ResponseEntity<>(themeService.findAllById(ids), HttpStatus.OK);
    }

    @Operation(summary = "Выдает неархивную тему по id")
    @GetMapping("/notArchived/{id}")
    public ResponseEntity<ThemeDto> findByIdNotArchived(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на выдачу неархивной ThemeDto для id = {0}", id);
        return new ResponseEntity<>(themeService.findByIdNotArchived(id), themeService.findByIdNotArchived(id) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Выдает неархивные темы по списку id")
    @GetMapping("/notArchived")
    public ResponseEntity<List<ThemeDto>> findAllByIdNotArchived(@RequestParam(value = "ids", required = false) List<Long> ids) {
        log.log(Level.INFO, "Получен запрос на выдачу неархивных ThemeDto для набора id = {0}", ids);
        return new ResponseEntity<>(themeService.findAllByIdNotArchived(ids), HttpStatus.OK);
    }

    @Operation(summary = "Помещает тему в архив (по id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> moveToArchive(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на перенос в архив темы с id = {0}", id);
        return new ResponseEntity<>(themeService.moveToArchive(id), HttpStatus.OK);
    }
}