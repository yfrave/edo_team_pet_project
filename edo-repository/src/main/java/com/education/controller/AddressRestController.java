package com.education.controller;

import com.education.entity.Address;
import com.education.model.dto.AddressDto;
import com.education.service.address.AddressService;

import com.education.util.Mapper.impl.AddressMapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/repository/address")
@AllArgsConstructor
@Log
@ApiModel("Controller for Address")
public class AddressRestController {
    @ApiModelProperty("service")
    private AddressService addressService;

    private AddressMapper mapper;

    @ApiOperation(value = "Получить адрес по d", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> fetchAddress(@PathVariable("id")
                                                   @ApiParam(name = "id", value = "Адрес id", example = "1")
                                                   Long id)
    {
        Optional<Address> address = addressService.findById(id);
        log.info("Address is received");
        return address.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(mapper.toDto(address.get()), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список адресов", notes = "Находит адреса по их id. Возвращает списком List<Address>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @PostMapping("/findAll")
    public ResponseEntity<List<AddressDto>> fetchAllAddresses(@RequestBody
                                                              @ApiParam(name = "Address list")
                                                              List<Long> idList)
    {
        List<Address> list = addressService.findAllById(idList);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list.stream()
                .map(mapper::toDto).collect(Collectors.toList()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Сохранить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    @PostMapping("/")
    public ResponseEntity<AddressDto> save(@RequestBody
                                           @ApiParam("Address")
                                           AddressDto address)
    {
        return new ResponseEntity<>(addressService.save(address), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Удалить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @ApiParam("id")
                                       Long id)
    {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
