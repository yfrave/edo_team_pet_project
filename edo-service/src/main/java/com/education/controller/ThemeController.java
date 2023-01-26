package com.education.controller;

import com.education.model.dto.ThemeDto;
import com.education.service.theme.ThemeService;
import io.swagger.annotations.ApiParam;
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

@RestController
@RequiredArgsConstructor
@Log
@RequestMapping("api/service/theme")
public class ThemeController {
    private final ThemeService themeService;

    @PostMapping("/")
    public ResponseEntity<ThemeDto> save(@RequestBody ThemeDto themeDto) {
        log.info("Отправляю ThemeDto на сохранение");
        return new ResponseEntity<>(themeService.save(themeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> findById(@PathVariable("id") Long id) {
        ThemeDto themeDto = themeService.findById(id);
        return themeDto != null
                ? new ResponseEntity<>(themeDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ThemeDto>> findAllById(@RequestParam List<Long> ids) {
        List<ThemeDto> themeDtos = themeService.findAllById(ids);
        return themeDtos != null && !themeDtos.isEmpty()
                ? new ResponseEntity<>(themeDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notArchived/{id}")
    public ResponseEntity<ThemeDto> findByIdNotArchived(@PathVariable("id") Long id) {
        ThemeDto themeDto = themeService.findByIdNotArchived(id);
        return themeDto != null
                ? new ResponseEntity<>(themeDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notArchived")
    public ResponseEntity<List<ThemeDto>> findAllByIdNotArchived(@RequestParam List<Long> ids) {
        List<ThemeDto> themeDtos = themeService.findAllByIdNotArchived(ids);
        return themeDtos != null && !themeDtos.isEmpty()
                ? new ResponseEntity<>(themeDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> moveToArchive(@PathVariable("id") Long id) {
        themeService.moveToArchive(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}