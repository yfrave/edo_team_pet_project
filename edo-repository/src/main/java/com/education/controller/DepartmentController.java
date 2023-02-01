package com.education.controller;

import com.education.entity.Department;
import com.education.service.department.DepartmentService;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            @ApiResponse(code = 404, message = "Not found - department not found")
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

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - department not found")
    })
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<Department> getByIdWhereArchivedIsNull(@PathVariable Long id) {
        Optional<Department> result = service.findByIdNotArchived(id);
        if (result.isEmpty()) {
            log.info("Department — not found!");
        } else {
            log.info("Department — found!");
        }
        return (result.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(result.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список департаментов", notes = "Находит департаменты по их id. Возвращает списком")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The department was not found")
    })
    @PostMapping("/findAll")
    public ResponseEntity<List<Department>> getAllDepartment(@RequestBody
                                                             @ApiParam(name = "Department list")
                                                             List<Long> idList) {
        List<Department> departmentList = service.findAllById(idList);
        return departmentList != null && !departmentList.isEmpty()
                ? new ResponseEntity<>(departmentList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Получить список департаментов, что ещё не отправлены в архив", notes = "Находит департаменты по их id. Возвращает списком")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The department was not found")
    })
    @PostMapping("/findAll/notArchived")
    public ResponseEntity<List<Department>> getAllDepartmentsWhereArchivedIsNull(@RequestBody
                                                                                 @ApiParam(name = "Department list")
                                                                                 List<Long> idList) {
        List<Department> departmentList = service.findAllByIdNotArchived(idList);
        return departmentList != null && !departmentList.isEmpty()
                ? new ResponseEntity<>(departmentList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Назначить дату архивации department-а")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Департамент успешно отмечен")
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
        Department result = service.save(obj);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            log.info("Successfully created");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
    }
}
