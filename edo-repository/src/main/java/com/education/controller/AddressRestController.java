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

    @ApiOperation(value = "Get an address by id", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddress(@PathVariable("id")
                                               @ApiParam(name = "id", value = "Product id", example = "1")
                                               Long id) {
        Optional<Address> address = addressService.findById(id);
        return address.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(address.get(), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Address>> findAllAddresses(List<Long> idList) { // Доделать приём листа
        List<Address> list = addressService.findAllById(idList);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Address> save(@RequestBody Address address) {
        addressService.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody Address address) {
        addressService.delete(address);
        return ResponseEntity.ok().build();
    }
}
