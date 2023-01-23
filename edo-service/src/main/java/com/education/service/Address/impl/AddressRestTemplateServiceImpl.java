package com.education.service.Address.impl;

import com.education.model.dto.AddressDto;
import com.education.service.Address.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressRestTemplateServiceImpl implements AddressService {
    private final String URL = "http://edo-repository/api/repository/address/";
    RestTemplate template;


    @Override
    public AddressDto getById(Long id) {
        return template.getForObject(URL + id, AddressDto.class);
    }

    @Override
    public List<AddressDto> fetchAddressedList(List<Long> idList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> body = new HttpEntity<Object>(idList, headers);
        ResponseEntity<List<AddressDto>> response = template.exchange(URL + "findAll", HttpMethod.POST, body,
                new ParameterizedTypeReference<List<AddressDto>>() {
                });
        return response.getBody();
    }

    @Override
    public AddressDto save(AddressDto address) {
        HttpEntity<AddressDto> request = new HttpEntity<>(address);
        return template.postForObject(URL, request, AddressDto.class);
    }

    @Override
    public void delete(AddressDto address) {
        template.delete(URL + "delete", address);
    }
}
