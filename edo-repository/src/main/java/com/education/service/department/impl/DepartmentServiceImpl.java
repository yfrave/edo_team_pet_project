package com.education.service.department.impl;

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
    public DepartmentDto save(DepartmentDto obj) {
        var result = repository.saveAndFlush(obj);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public DepartmentDto findById(Long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<DepartmentDto> findAllById(List<Long> ids) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public DepartmentDto findByIdNotArchived(Long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<DepartmentDto> findAllByIdNotArchived(List<Long> ids) {
        return null;
    }
}
