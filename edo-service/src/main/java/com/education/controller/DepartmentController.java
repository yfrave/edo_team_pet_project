package com.education.controller;

import com.education.model.dto.DepartmentDto;
import com.education.service.department.DepartmentService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/department")
@AllArgsConstructor
@Log
@ApiModel("Контроллер эдо-сервиса для сущности Department")
public class DepartmentController {
    @ApiModelProperty("сервис для контроллера")
    private DepartmentService service;

    @ApiOperation(value = "Получить department по id", notes = "Returns an department as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        DepartmentDto dto = service.findById(id);
        if (dto != null) {
            log.info("Received department-dto");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить department по id. Игнорирует отправленные в архив", notes = "Returns an department as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/notArchived/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentByIdNotArchived(@PathVariable Long id) {
        DepartmentDto dto = service.findByIdNotArchived(id);
        if (dto != null) {
            log.info("Received department-dto NotArchived");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список department", notes = "Находит department по их id. Возвращает списком List<departmentDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<DepartmentDto>> getDepartmentList(@RequestBody List<Long> idList) {
        log.info("отправил list DepartmentDto.class");
        return new ResponseEntity<>(service.findAllById(idList), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список department, что ещё не в архиве", notes = "Находит department по их id. Возвращает списком List<departmentDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @GetMapping("/findAll/notArchived")
    public ResponseEntity<List<DepartmentDto>> getDepartmentListNotArchived(@RequestBody List<Long> idList) {
        log.info("отправил list DepartmentDto.class NotArchived");
        return new ResponseEntity<>(service.findAllByIdNotArchived(idList), HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    @PostMapping("/")
    public ResponseEntity<DepartmentDto> save(@RequestBody @ApiParam("Address") DepartmentDto address) {
        var dto = service.save(address);
        if (dto.getClass() == DepartmentDto.class) {
            log.info("Сохранил DepartmentDto.class");
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            log.info("Не получилось сохранить DepartmentDto.class");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Назначить дату архивации department-а")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Департамент успешно архивирован")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDto> delete(@PathVariable Long id) {
        service.moveToArchive(id);
        log.info("Move entity department with id: %s to archive");
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
