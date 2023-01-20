package com.education.service.Address.impl;

import com.education.feign.AddressFeignClient;
import com.education.model.dto.AddressDto;
import com.education.service.Address.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    AddressFeignClient feignClient;

    @Override
    public AddressDto getById(Long id) {
        return feignClient.fetchAddress(id);
    }

    @Override
    public List<AddressDto> fetchAddressedList(List<Long> idList) {
        return feignClient.getAddressesList(idList);
    }

    @Override
    public AddressDto save(AddressDto address) {
        return feignClient.save(address);
    }

    @Override
    public void delete(AddressDto address) {
        feignClient.delete(address);
    }
}
