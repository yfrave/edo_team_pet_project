package com.education.service.address;

import com.education.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    void save(Address address);
    void delete(Address address);
    Optional<Address> findById(Long id);
    List<Address> findAllById(Iterable<Long> list);
}
