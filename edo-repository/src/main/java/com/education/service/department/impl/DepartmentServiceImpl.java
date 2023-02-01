package com.education.service.department.impl;

import com.education.entity.Address;
import com.education.entity.Department;
import com.education.repository.AddressRepository;
import com.education.repository.DepartmentRepository;
import com.education.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Department save(Department obj) {
        Long addressId = obj.getAddress().getId();
        if (addressId == null) {
            addressRepository.save(obj.getAddress());
            return repository.saveAndFlush(obj);
        }
        Optional<Address> address = addressRepository.findById(addressId);
        boolean isAddressTaken = repository.findDepartmentByAddressId(addressId).isPresent();
        if (address.isPresent() && !isAddressTaken) {
            return repository.saveAndFlush(obj);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        repository.moveToArchive(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Department> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Department> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Department> findByIdNotArchived(Long id) {
        return repository.findDepartmentByIdAndArchivedDateIsNull(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Department> findAllByIdNotArchived(List<Long> ids) {
        List<Department> result = new ArrayList<>();
        for (Long n : ids) {
            repository.findDepartmentByIdAndArchivedDateIsNull(n)
                    .ifPresent(result::add);
        }
        return result;
    }
}
