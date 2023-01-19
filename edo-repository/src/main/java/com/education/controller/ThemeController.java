package com.education.controller;

import com.education.service.theme.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.education.entity.Theme;

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
        return new ResponseEntity<>(themeService.findById(id).get(), HttpStatus.OK);
    }
}



