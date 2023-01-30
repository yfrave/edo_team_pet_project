package com.education.util;

import com.education.entity.Employee;
import com.education.model.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConverter {
    public static Employee dtoToEmployee(EmployeeDto dto) {
        return Employee.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .middleName(dto.getMiddleName())
                .address(dto.getAddress())
                .photoUrl(dto.getPhotoUrl())
                .fioDative(dto.getFioDative())
                .fioNominative(dto.getFioNominative())
                .fioGenitive(dto.getFioGenitive())
                .externalId(dto.getExternalId())
                .phone(dto.getPhone())
                .workPhone(dto.getWorkPhone())
                .birthDate(dto.getBirthDate())
                .username(dto.getUsername())
                .creationDate(dto.getCreationDate())
                .archivedDate(dto.getArchivedDate())
                .build();
    }

    public static List<Employee> dtoToEmployee(List<EmployeeDto> dtos) {
        List<Employee> employees=new ArrayList<>();
        for (EmployeeDto dto : dtos) {
            employees.add(dtoToEmployee(dto));
        }
        return employees;
    }

    public static EmployeeDto employeeToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .middleName(employee.getMiddleName())
                .address(employee.getAddress())
                .photoUrl(employee.getPhotoUrl())
                .fioDative(employee.getFioDative())
                .fioNominative(employee.getFioNominative())
                .fioGenitive(employee.getFioGenitive())
                .externalId(employee.getExternalId())
                .phone(employee.getPhone())
                .workPhone(employee.getWorkPhone())
                .birthDate(employee.getBirthDate())
                .username(employee.getUsername())
                .creationDate(employee.getCreationDate())
                .archivedDate(employee.getArchivedDate())
                .build();
    }
    public static List<EmployeeDto> employeeToDto(List<Employee> employees) {
        List<EmployeeDto> dtos=new ArrayList<>();
        for (Employee employee : employees) {
            dtos.add(employeeToDto(employee));
        }
        return dtos;
    }
}
