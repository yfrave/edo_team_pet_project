package com.education.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@AllArgsConstructor
@SuperBuilder
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends BaseEntity {

    /**
     * Адрес, полностью.
     */
    @Column(name = "full_address")
    private String fullAddress;

    /**
     * Улица
     */
    @Column(name = "street")
    private String street;

    /**
     * Номер дома
     */
    @Column(name = "house")
    private String house;

    /**
     * Индекс
     */
    @Column(name = "index")
    private String index;

    /**
     * Корпус
     */
    @Column(name = "housing")
    private String housing;

    /**
     * Строение
     */
    @Column(name = "building")
    private String building;

    /**
     * Город
     */
    @Column(name = "city")
    private String city;

    /**
     * Регион
     */
    @Column(name = "region")
    private String region;

    /**
     * Страна
     */
    @Column(name = "country")
    private String country;

    /**
     * Номер квартиры, или этаж
     */
    @Column(name = "flat")
    private String flat;
}
