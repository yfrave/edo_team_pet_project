package com.education.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Address DTO")
@Data
public class AddressDTO {
    private Long id;

    @JsonProperty
    private String fullAddress;
    @JsonProperty
    private String street;
    @JsonProperty
    private String house;
    @JsonProperty
    private String index;
    @JsonProperty
    private String housing;
    @JsonProperty
    private String building;
    @JsonProperty
    private String city;
    @JsonProperty
    private String region;
    @JsonProperty
    private String country;
    @JsonProperty
    private String flat;
}
