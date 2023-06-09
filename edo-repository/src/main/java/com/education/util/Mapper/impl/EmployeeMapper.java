package com.education.util.Mapper.impl;

import com.education.entity.Employee;
import com.education.model.dto.EmployeeDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends Mappable<Employee, EmployeeDto> {
}
