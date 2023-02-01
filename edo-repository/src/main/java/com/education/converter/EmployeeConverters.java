package com.education.converter;

import com.education.entity.Employee;
import com.education.model.dto.EmployeeDto;

/**
 * @author Степан Ритман
 * Класс содержит статические методы для преобразования
 * объектов сущности Employee в Dto
 * и объектов Dto в сущности
 */
public class EmployeeConverters {
    /**
     * Статический метод для преобразования объекта Dto Employee в объектс сущности
     * @param employeeDto объект класса EmployeeDto
     * @return Объект сущности Employee. Возвращает null если на вход передается пустой Dto
     */
    public static Employee dtoToEntityConverter(EmployeeDto employeeDto) {
        return employeeDto != null
                ? Employee.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .middleName(employeeDto.getMiddleName())
                .address(employeeDto.getAddress())
                .photoUrl(employeeDto.getPhotoUrl())
                .fioDative(employeeDto.getFioDative())
                .fioGenitive(employeeDto.getFioGenitive())
                .fioNominative(employeeDto.getFioNominative())
                .externalId(employeeDto.getExternalId())
                .phone(employeeDto.getPhone())
                .workPhone(employeeDto.getWorkPhone())
                .birthDate(employeeDto.getBirthDate())
                .username((employeeDto.getUsername()))
                .creationDate(employeeDto.getCreationDate())
                .archivedDate(employeeDto.getArchivedDate())
                .build()
                : null;
    }

    /**
     * Метод для преобразования объекта сущности Employee в объект Dto
     * @param employee объект сущности Employee
     * @return Объект Dto Employee. Возвращает null если на вход передан пустой объект сущности
     */
    public static EmployeeDto entityToDtoConverter(Employee employee) {
        return employee != null
                ? EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .middleName(employee.getMiddleName())
                .address(employee.getAddress())
                .photoUrl(employee.getPhotoUrl())
                .fioDative(employee.getFioDative())
                .fioGenitive(employee.getFioGenitive())
                .fioNominative(employee.getFioNominative())
                .externalId(employee.getExternalId())
                .phone(employee.getPhone())
                .workPhone(employee.getWorkPhone())
                .birthDate(employee.getBirthDate())
                .username((employee.getUsername()))
                .creationDate(employee.getCreationDate())
                .archivedDate(employee.getArchivedDate())
                .build()
                : null;
    }
}
