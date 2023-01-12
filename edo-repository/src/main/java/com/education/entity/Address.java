package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String street;

    /**
     * Номер дома
     */
    private String house;

    /**
     * Индекс
     */
    private String index;

    /**
     * Корпус
     */
    private String housing;

    /**
     * Строение
     */
    private String building;

    /**
     * Город
     */
    private String city;

    /**
     * Регион
     */
    private String region;

    /**
     * Страна
     */
    private String country;

    /**
     * Номер квартиры, или этаж
     */
    private String flat;
}
