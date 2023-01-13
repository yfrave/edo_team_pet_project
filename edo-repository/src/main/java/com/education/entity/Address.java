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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
