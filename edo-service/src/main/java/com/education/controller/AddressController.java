package com.education.controller;

import com.education.model.dto.AddressDto;
import com.education.service.Address.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/service/address")
@AllArgsConstructor
public class AddressController {
    AddressService service;
    private static final Logger LOG = Logger.getLogger(AddressController.class.getName());

    @ApiOperation(value = "Получить адрес по d", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long id) {
        LOG.log(Level.INFO, "отправил AddressDto.class");
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список адресов", notes = "Находит адреса по их id. Возвращает списком List<Address>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @PostMapping("/findAll")
    public ResponseEntity<List<AddressDto>> getAddressesList(@RequestBody List<Long> idList) {
        LOG.log(Level.INFO, "отправил list AddressDto.class");
        return new ResponseEntity<>(service.fetchAddressedList(idList), HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    @PostMapping("/")
    public ResponseEntity<AddressDto> save(@RequestBody @ApiParam("Address") AddressDto address) {
        LOG.log(Level.INFO, "сохранил AddressDto.class");
        return new ResponseEntity<>(service.save(address), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Удалить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @ApiParam("Address") AddressDto address) {
        service.delete(address);
        LOG.log(Level.INFO, "удалил AddressDto.class");
        return ResponseEntity.ok().build();
    }
}
