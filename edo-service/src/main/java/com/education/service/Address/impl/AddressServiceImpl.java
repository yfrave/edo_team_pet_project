package com.education.service.Address.impl;

import com.education.feign.AddressFeignClient;
import com.education.model.dto.AddressDto;
import com.education.service.Address.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    AddressFeignClient feignClient;

    @Override
    public AddressDto getById(Long id) {
        return feignClient.fetchAddress(id);
    }
}
