package com.education.controller;

import com.education.model.dto.DepartmentDto;
import com.education.service.department.DepartmentService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service/department")
@AllArgsConstructor
@Log
@ApiModel("Контроллер эдо-сервиса для сущности Department")
public class DepartmentController {
    @ApiModelProperty("сервис для контроллера")
    private DepartmentService service;

    @ApiOperation(value = "Получить адрес по d", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id) {
        DepartmentDto dto = service.findById(id);
        if (dto != null) {
            log.info("Received department-dto");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
