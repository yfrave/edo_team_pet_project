package com.education.service.address.impl;

import com.education.entity.Address;
import com.education.repository.AddressRepository;
import com.education.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Address obj) {
        addressRepository.saveAndFlush(obj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Address obj) {
        addressRepository.delete(obj);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Address> findAllById(Iterable<Long> list) {
        return addressRepository.findAllById(list);
    }
}
