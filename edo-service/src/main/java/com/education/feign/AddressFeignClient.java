package com.education.feign;

import com.education.model.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "edo-repository", path = "/api/repository/address")
public interface AddressFeignClient {
    @GetMapping("/{id}")
    AddressDto fetchAddress(@PathVariable Long id);

    @GetMapping("/findAll")
    List<AddressDto> getAddressesList(@RequestBody List<Long> idList);

    @PostMapping("/")
    AddressDto save(@RequestBody AddressDto address);

    @DeleteMapping("/delete")
    void delete(@RequestBody AddressDto address);
}
