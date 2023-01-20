package com.education.feign;

import com.education.model.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${spring.application.name}")
public interface AddressFeignClient {
    @GetMapping("/api/repository/address/{id}")
    AddressDto fetchAddress(@PathVariable Long id);
}
