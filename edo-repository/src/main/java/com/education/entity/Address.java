package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Представляет адрес.
 *
 * @author Сергей Иваненко
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {

    /**
     * Адрес, полностью.
     */
    @Column(name = "full_address")
    private String fullAddress;

    /**
     * Улица
     */
    @NotNull(message = "Street cannot be null")
    private String street;

    /**
     * Номер дома
     */
    @NotNull(message = "house cannot be null")
    private String house;

    /**
     * Индекс
     */
    @NotNull(message = "index cannot be null")
    private String index;

    /**
     * Корпус
     */
    @NotNull(message = "housing cannot be null")
    private String housing;

    /**
     * Строение
     */
    @NotNull(message = "building cannot be null")
    private String building;

    /**
     * Город
     */
    @NotNull(message = "city cannot be null")
    private String city;

    /**
     * Регион
     */
    @NotNull(message = "region cannot be null")
    private String region;

    /**
     * Страна
     */
    @NotNull(message = "country cannot be null")
    private String country;

    /**
     * Номер квартиры, или этаж
     */
    @NotNull(message = "flat cannot be null")
    private String flat;

    public Address(String country, String region, String city, String street, String house,
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
