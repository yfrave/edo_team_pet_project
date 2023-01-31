package com.education.service.department;

import com.education.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
//    DepartmentDto save(DepartmentDto obj);
//    void moveToArchive(Long id);
//    DepartmentDto findById(Long id);
//    List<DepartmentDto> findAllById(List<Long> ids);
//    DepartmentDto findByIdNotArchived(Long id);
//    List<DepartmentDto> findAllByIdNotArchived(List<Long> ids);
    Department save(Department obj);
    void moveToArchive(Long id);
    Optional<Department> findById(Long id);
    List<Department> findAllById(List<Long> ids);
    Department findByIdNotArchived(Long id);
    List<Department> findAllByIdNotArchived(List<Long> ids);

}
