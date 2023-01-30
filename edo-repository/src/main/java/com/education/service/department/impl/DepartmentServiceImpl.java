package com.education.service.department.impl;

import static com.education.Util.DepartmentConverter.*;

import com.education.entity.Department;
import com.education.model.dto.DepartmentDto;
import com.education.repository.DepartmentRepository;
import com.education.service.department.DepartmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @NonNull
    private DepartmentRepository repository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Department save(Department obj) {
        var result = repository.saveAndFlush(obj);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Department findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Department> findAllById(List<Long> ids) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Department findByIdNotArchived(Long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Department> findAllByIdNotArchived(List<Long> ids) {
        return null;
    }
}
