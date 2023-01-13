package com.education.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("Класс AddressDTO, dto для класса Address.class")
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {
    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("Полный адрес")
    private String fullAddress;
    @ApiModelProperty("Улица")
    private String street;
    @ApiModelProperty("Номер дома")
    private String house;
    @ApiModelProperty("Индекс")
    private String index;
    @ApiModelProperty("Корпус")
    private String housing;
    @ApiModelProperty("Строение")
    private String building;
    @ApiModelProperty("Город")
    private String city;
    @ApiModelProperty("Регион")
    private String region;
    @ApiModelProperty("Страна")
    private String country;
    @ApiModelProperty("Этаж")
    private String flat;

    public AddressDTO(String country, String region, String city, String street, String house,
                   String building, String flat, String housing, String index) {
        this.street = street;
        this.house = house;
        this.index = index;
        this.housing = housing;
        this.building = building;
        this.city = city;
        this.region = region;
        this.country = country;
        this.flat = flat;
        this. fullAddress = String.join(", ", country, region, city, street, housing, building, flat,
                house, index);
    }
}
