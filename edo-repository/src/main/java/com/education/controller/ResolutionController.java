package com.education.controller;

import com.education.service.resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.education.entity.Resolution;

import java.util.List;

@ApiOperation("Resolution API")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/repository/resolution")
public class ResolutionController {

    final private ResolutionService RESOLUTION_SERVICE;


    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<Resolution> saveResolution(@RequestBody Resolution resolution) {
        if (RESOLUTION_SERVICE.save(resolution)) {
            return new ResponseEntity<>(resolution, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(resolution, HttpStatus.CONFLICT);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/ToArchive/{id}")
    public ResponseEntity<Resolution> moveToArchiveResolution(@PathVariable Long id) {
        RESOLUTION_SERVICE.moveToArchive(id);
        return new ResponseEntity<>(RESOLUTION_SERVICE.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/ById/{id}")
    public ResponseEntity<Resolution> findByIdResolution(@PathVariable Long id) {
        Resolution resolution = RESOLUTION_SERVICE.findById(id);
        if (resolution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resolution, HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по списку id (/1, 2, 3)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/AllById/{ids}")
    public ResponseEntity<List<Resolution>> findAllByIdResolution(@PathVariable List<Long> ids) {
        List<Resolution> resolution = RESOLUTION_SERVICE.findAllById(ids);
        if (resolution.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resolution, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/NotArchived/{id}")
    public ResponseEntity<Resolution> findByIdNotArchivedResolution(@PathVariable Long id) {
        Resolution resolution = RESOLUTION_SERVICE.findByIdNotArchived(id);
        if (resolution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resolution, HttpStatus.OK);
    }
    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/ALlNotArchived/{ids}")
    public ResponseEntity<List<Resolution>> findAllByIdNotArchivedResolution(@PathVariable List<Long> ids) {
        List<Resolution> resolution = RESOLUTION_SERVICE.findAllByIdNotArchived(ids);
        if (resolution.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resolution, HttpStatus.OK);
    }
}
