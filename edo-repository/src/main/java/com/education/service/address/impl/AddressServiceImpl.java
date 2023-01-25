package com.education.service.address.impl;

import com.education.entity.Address;
import com.education.model.dto.AddressDto;
import com.education.repository.AddressRepository;
import com.education.service.address.AddressService;
import static com.education.util.DtoConverter.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;


    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressDto save(AddressDto obj) {
        Address address = convertFrom(obj);
        return convertTo(addressRepository.saveAndFlush(address));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Address> findAllById(Iterable<Long> list) {
        return addressRepository.findAllById(list);
    }
}
