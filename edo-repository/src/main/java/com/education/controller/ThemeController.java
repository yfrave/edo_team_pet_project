package com.education.controller;

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
import com.education.entity.Theme;

import java.util.List;

@RestController
@RequestMapping("api/repository/theme")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @PostMapping
    public ResponseEntity<Theme> saveTheme(@RequestBody Theme theme) {
        themeService.save(theme);
        return new ResponseEntity<>(theme, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(themeService.findById(id), themeService.findById(id) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Theme>> getAllById(@RequestParam(value = "ids", required = false) List<Long> ids) {
        return new ResponseEntity<>(themeService.findAllById((List<Long>) ids), HttpStatus.OK);
    }

    @GetMapping("/notarchived/{id}")
    public ResponseEntity<Theme> findByIdNotArchived(@PathVariable("id") Long id) {
        return new ResponseEntity<>(themeService.findByIdNotArchived(id), themeService.findByIdNotArchived(id) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notarchived")
    public ResponseEntity<List<Theme>> findAllByIdNotArchived(@RequestParam(value = "ids", required = false) List<Long> ids) {
        return new ResponseEntity<>(themeService.findAllByIdNotArchived((List<Long>) ids), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> moveToArchive(@PathVariable("id") Long id) {
        return new ResponseEntity<>(themeService.moveToArchive(id), HttpStatus.OK);
    }


}



