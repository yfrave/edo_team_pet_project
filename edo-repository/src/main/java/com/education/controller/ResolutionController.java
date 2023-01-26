package com.education.controller;

import com.education.service.resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.education.entity.Resolution;

import java.util.List;

@ApiOperation("Resolution API")
@Log4j2
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
            log.log(Level.INFO, "Сущность сохранена");
            return new ResponseEntity<>(resolution, HttpStatus.CREATED);
        }
        log.log(Level.WARN, "Сущность не сохранена");
        return new ResponseEntity<>(resolution, HttpStatus.CONFLICT);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/ToArchive/{id}")
    public ResponseEntity<Resolution> moveToArchiveResolution(@PathVariable Long id) {
        RESOLUTION_SERVICE.moveToArchive(id);
        log.log(Level.INFO, "Дата архивации обновлена");
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
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(resolution, HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по списку id (/1, 2, 3)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/AllById/{ids}")
    public ResponseEntity<List<Resolution>> findAllByIdResolution(@PathVariable List<Long> ids) {
        List<Resolution> resolution = RESOLUTION_SERVICE.findAllById(ids);
        if (resolution.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
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
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(resolution, HttpStatus.OK);
    }
    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/ALlNotArchived/{ids}")
    public ResponseEntity<List<Resolution>> findAllByIdNotArchivedResolution(@PathVariable List<Long> ids) {
        List<Resolution> resolution = RESOLUTION_SERVICE.findAllByIdNotArchived(ids);
        if (resolution.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(resolution, HttpStatus.OK);
    }
}
