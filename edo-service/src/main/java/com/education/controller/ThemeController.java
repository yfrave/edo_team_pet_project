package com.education.controller;

import com.education.model.dto.NomenclatureDto;
import com.education.service.theme.ThemeService;
import com.education.model.dto.ThemeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/service/theme")
public class ThemeController {
    private final ThemeService themeService;

    @ApiOperation(value = "Сохранить сущность в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена")
    })
    @PostMapping("/")
    public ResponseEntity<ThemeDto> save(@RequestBody @ApiParam("Theme") ThemeDto themeDto) {
        return new ResponseEntity<>(themeService.save(themeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> findById(@PathVariable("id") Long id) {
        ThemeDto themeDto = themeService.findById(id);
        return themeDto != null
                ? new ResponseEntity<>(themeDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}