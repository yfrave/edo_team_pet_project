package com.education.Util;

import com.education.entity.Department;
import com.education.model.dto.DepartmentDto;

public class DepartmentConverter {
    public static DepartmentDto convertToDto(Department obj) {
        var result = new DepartmentDto();
        return new DepartmentDto();
    }

    public static Department convertFromDto(DepartmentDto obj) {

        return new Department();
    }
}
