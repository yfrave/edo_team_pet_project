package com.education.service.address.impl;

import com.education.entity.Address;
import com.education.repository.AddressRepository;
import com.education.service.address.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    @Override
    public void save(Address obj) {
        addressRepository.save(obj);
    }

    @Override
    public void delete(Address obj) {
        addressRepository.delete(obj);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAllById(Iterable<Long> list) {
        return addressRepository.findAllById(list);
    }

    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
