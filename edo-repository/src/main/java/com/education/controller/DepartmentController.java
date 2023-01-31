package com.education.controller;

import com.education.entity.Department;
import com.education.service.department.DepartmentService;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/repository/department")
@RequiredArgsConstructor
@Log
@ApiModel("Department API")
public class DepartmentController {
    @NonNull
    private final DepartmentService service;

    @ApiOperation(value = "Получить department по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        var result = service.findById(id);
        if (result.isEmpty()) {
            log.info("Department — not found!");
        } else {
            log.info("Department — found!");
        }
        return (result.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(result.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Поместить department в архив")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Department> delete(@PathVariable Long id) {
        service.moveToArchive(id);
        log.info("Move entity department with id: %s to archive");
        return new ResponseEntity<>(service.findById(id).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранить сущность в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    @PostMapping("/")
    public ResponseEntity<Department> save(@RequestBody @ApiParam("DepartmentDto") Department obj) {
        var result = service.save(obj);
        log.info("Successfully created");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
