package com.education.util;

import com.education.entity.Employee;
import com.education.entity.FilePool;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FilePoolDto;

/**
 * Converter of Dto
 */

public class FilePoolConverter {

    /**
     * Converted from FilePoolDto to FilePoolDto
     *
     * @param filePool
     * @return FilePoolDto
     */
    public static FilePoolDto convertToDto(FilePool filePool) {
        return new FilePoolDto(filePool.getId(),
                filePool.getStorageFileId(),
                filePool.getName(),
                filePool.getExtension(),
                filePool.getSize(),
                filePool.getPageCount(),
                filePool.getUploadDate(),
                filePool.getArchivedDate(),
                convertToDto(filePool.getCreator()));
    }

    /**
     * Converted from Employee to EmployeeDto
     *
     * @param employee Employee
     * @return EmployeeDto
     */
    public static EmployeeDto convertToDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getMiddleName(),
                employee.getAddress(),
                employee.getPhotoUrl(),
                employee.getFioDative(),
                employee.getFioNominative(),
                employee.getFioGenitive(),
                employee.getExternalId(),
                employee.getPhone(),
                employee.getWorkPhone(),
                employee.getBirthDate(),
                employee.getUsername(),
                employee.getCreationDate(),
                employee.getArchivedDate());
    }

    /**
     * Converted from FilePoolDto to FilePoolDto
     *
     * @param filePoolDto
     * @return FilePoolDto
     */
    public static FilePool convertFromDto(FilePoolDto filePoolDto) {
        return new FilePool(
                filePoolDto.getStorageFileId(),
                filePoolDto.getName(),
                filePoolDto.getExtension(),
                filePoolDto.getSize(),
                filePoolDto.getPageCount(),
                filePoolDto.getUploadDate(),
                filePoolDto.getArchivedDate(),
                convertFromDto((filePoolDto.getCreator())));
    }

    /**
     * Converted from EmployeeDto to Employee
     *
     * @param employeeDto
     * @return EmployeeDto
     */
    public static Employee convertFromDto(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getMiddleName(),
                employeeDto.getAddress(),
                employeeDto.getPhotoUrl(),
                employeeDto.getFioDative(),
                employeeDto.getFioNominative(),
                employeeDto.getFioGenitive(),
                employeeDto.getExternalId(),
                employeeDto.getPhone(),
                employeeDto.getWorkPhone(),
                employeeDto.getBirthDate(),
                employeeDto.getUsername(),
                employeeDto.getCreationDate(),
                employeeDto.getArchivedDate());
    }
}
