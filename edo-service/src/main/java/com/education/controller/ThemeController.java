package com.education.controller;

import com.education.model.dto.ThemeDto;
import com.education.service.theme.ThemeService;
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
@RequiredArgsConstructor
@Log
@RequestMapping("api/service/theme")
public class ThemeController {
    private final ThemeService THEME_SERVICE;

    @PostMapping("/")
    public ResponseEntity<ThemeDto> save(@RequestBody ThemeDto themeDto) {
        log.log(Level.INFO, "Отправляю ThemeDto на сохранение. Название темы: {0}", themeDto.getName());
        return new ResponseEntity<>(THEME_SERVICE.save(themeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> findById(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на выдачу ThemeDto для id = {0}", id);
        ThemeDto themeDto = THEME_SERVICE.findById(id);
        log.log(Level.INFO, "Для id = {0} выдаю ThemeDto с названием {1}", new Object[]{String.valueOf(id), themeDto.getName()});
        return themeDto != null
                ? new ResponseEntity<>(themeDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ThemeDto>> findAllById(@RequestParam List<Long> ids) {
        log.log(Level.INFO, "Получен запрос на выдачу ThemeDto для набора id = {0}", ids);
        List<ThemeDto> themeDtos = THEME_SERVICE.findAllById(ids);
        return themeDtos != null && !themeDtos.isEmpty()
                ? new ResponseEntity<>(themeDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notArchived/{id}")
    public ResponseEntity<ThemeDto> findByIdNotArchived(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на выдачу неархивной ThemeDto для id = {0}", id);
        ThemeDto themeDto = THEME_SERVICE.findByIdNotArchived(id);
        log.log(Level.INFO, "Для id = {0} выдаю неархивную ThemeDto с названием {1}", new Object[]{String.valueOf(id), themeDto.getName()});
        return themeDto != null
                ? new ResponseEntity<>(themeDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notArchived")
    public ResponseEntity<List<ThemeDto>> findAllByIdNotArchived(@RequestParam List<Long> ids) {
        log.log(Level.INFO, "Получен запрос на выдачу неархивных ThemeDto для набора id = {0}", ids);
        List<ThemeDto> themeDtos = THEME_SERVICE.findAllByIdNotArchived(ids);
        return themeDtos != null && !themeDtos.isEmpty()
                ? new ResponseEntity<>(themeDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> moveToArchive(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на перенос в архив темы с id = {0}", id);
        THEME_SERVICE.moveToArchive(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}