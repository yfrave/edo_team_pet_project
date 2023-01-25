package com.education.util;


import com.education.entity.Address;
import com.education.model.dto.AddressDto;

/**
 * Converter Dto for Address
 */
public class DtoConverter {

    /**
     * Convert Address to AddressDto
     * @param address Address
     * @return AddressDto
     */
    public static AddressDto convertTo(Address address) {
        return new AddressDto(address.getId(),
                address.getFullAddress(),
                address.getStreet(),
                address.getHouse(),
                address.getIndex(),
                address.getHousing(),
                address.getBuilding(),
                address.getCity(),
                address.getRegion(),
                address.getCountry(),
                address.getFlat());
    }

    /**
     * Convert AddressDto to Address
     * @param dto AddressDto
     * @return Address
     */
    public static Address convertFrom(AddressDto dto) {
        return new Address(dto.getFullAddress(),
                dto.getStreet(),
                dto.getHouse(),
                dto.getIndex(),
                dto.getHousing(),
                dto.getBuilding(),
                dto.getCity(),
                dto.getRegion(),
                dto.getCountry(),
                dto.getFlat());
    }
}
