package com.education.service.address;

import com.education.entity.Address;
import com.education.model.dto.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    AddressDto save(AddressDto address);

    void delete(Long id);

    Optional<Address> findById(Long id);

    List<Address> findAllById(Iterable<Long> list);
}
