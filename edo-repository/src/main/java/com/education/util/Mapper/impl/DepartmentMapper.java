package com.education.util.Mapper.impl;

import com.education.entity.Department;
import com.education.model.dto.DepartmentDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends Mappable<Department, DepartmentDto> {
}
