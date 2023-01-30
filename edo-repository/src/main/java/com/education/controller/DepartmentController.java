package com.education.controller;

import com.education.entity.Department;
import com.education.service.department.DepartmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/repository/department")
@RequiredArgsConstructor
@Log
public class DepartmentController {
    @NonNull
    private final DepartmentService service;

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
