package com.education.service.Address;

import com.education.model.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto getById(Long id);

    List<AddressDto> fetchAddressedList(List<Long> idList);

    AddressDto save(AddressDto address);

    void delete(Long id);
}
