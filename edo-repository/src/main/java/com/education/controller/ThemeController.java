package com.education.controller;

import com.education.model.dto.ThemeDto;
import com.education.service.theme.ThemeService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/repository/theme")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @PostMapping
    public ResponseEntity<ThemeDto> saveTheme(@RequestBody ThemeDto themeDto) {
        themeService.save(themeDto);
        return new ResponseEntity<>(themeDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> getThemeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(themeService.findById(id), themeService.findById(id) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ThemeDto>> getAllById(@RequestParam(value = "ids", required = false) List<Long> ids) {
        return new ResponseEntity<>(themeService.findAllById(ids), HttpStatus.OK);
    }

    @GetMapping("/notarchived/{id}")
    public ResponseEntity<ThemeDto> findByIdNotArchived(@PathVariable("id") Long id) {
        return new ResponseEntity<>(themeService.findByIdNotArchived(id), themeService.findByIdNotArchived(id) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notarchived")
    public ResponseEntity<List<ThemeDto>> findAllByIdNotArchived(@RequestParam(value = "ids", required = false) List<Long> ids) {
        return new ResponseEntity<>(themeService.findAllByIdNotArchived(ids), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> moveToArchive(@PathVariable("id") Long id) {
        return new ResponseEntity<>(themeService.moveToArchive(id), HttpStatus.OK);
    }
}