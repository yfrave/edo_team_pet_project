package com.education.controller;

import com.education.entity.Address;
import com.education.service.address.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repository/address")
public class AddressRestController {
    private AddressService addressService;

    @ApiOperation(value = "Получить адрес по d", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddress(@PathVariable("id")
                                               @ApiParam(name = "id", value = "Адрес id", example = "1")
                                               Long id) {
        Optional<Address> address = addressService.findById(id);
        return address.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(address.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список адресов", notes = "Находит адреса по их id. Возвращает списком List<Address>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<Address>> findAllAddresses(@RequestParam
                                                          @ApiParam(name = "Address list")
                                                          List<Long> idList) {
        List<Address> list = addressService.findAllById(idList);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Сохранить адрес")
    @ApiResponse(code = 200, message = "Successfully created")
    @PostMapping("/")
    public ResponseEntity<Address> save(@RequestBody @ApiParam("Address") Address address) {
        addressService.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Удалить адрес")
    @ApiResponse(code = 200, message = "Successfully deleted")
    @PostMapping("/")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody @ApiParam("Address") Address address) {
        addressService.delete(address);
        return ResponseEntity.ok().build();
    }
}
