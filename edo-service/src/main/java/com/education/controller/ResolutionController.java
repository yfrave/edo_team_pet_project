package com.education.controller;

import com.education.model.dto.ResolutionDto;
import com.education.service.resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("ResolutionDto API")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/service/resolution")
public class ResolutionController {

    final private ResolutionService resolutionService;

    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<ResolutionDto> saveResolution(@ApiParam("resolutionDto") @RequestBody ResolutionDto resolutionDto) {
        if (resolutionService.save(resolutionDto)) {
            return new ResponseEntity<>(resolutionDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(resolutionDto, HttpStatus.CONFLICT);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/ToArchive/{id}")
    public ResponseEntity<ResolutionDto> moveToArchiveResolution(@ApiParam("id") @PathVariable Long id) {
        resolutionService.moveToArchive(id);
        return new ResponseEntity<>(resolutionService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/ById/{id}")
    public ResponseEntity<ResolutionDto> findByIdResolution(@ApiParam("id") @PathVariable Long id) {
        ResolutionDto resolution = resolutionService.findById(id);
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
    public ResponseEntity<List<ResolutionDto>> findAllByIdResolution(@ApiParam("ids") @PathVariable List<Long> ids) {
        List<ResolutionDto> resolutionDto = resolutionService.findAllById(ids);
        if (resolutionDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/NotArchived/{id}")
    public ResponseEntity<ResolutionDto> findByIdNotArchivedResolution(@ApiParam("id") @PathVariable Long id) {
        ResolutionDto resolutionDto = resolutionService.findByIdNotArchived(id);
        if (resolutionDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })

    @GetMapping(value = "/AllNotArchived/{ids}")
    public ResponseEntity<List<ResolutionDto>> findAllByIdNotArchivedResolution(@ApiParam("ids") @PathVariable List<Long> ids) {
        List<ResolutionDto> resolutionDto = resolutionService.findAllByIdNotArchived(ids);
        if (resolutionDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }
}