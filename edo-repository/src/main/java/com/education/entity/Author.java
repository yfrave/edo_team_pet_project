package com.education.entity;

import com.education.entity.enumAuthor.EnumEmployment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс сущности автора
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "author")
public class Author extends BaseEntity {

    /**
     * Имя
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", nullable = false)
    private String middleName;

    /**
     * Адрес
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * СНИЛС
     */
    @Column(name = "snils", nullable = false, length = 11)
    private String snils;

    /**
     * Номер телефона
     */
    @Column(name = "mobile_phone", nullable = false, length = 15)
    private String mobilePhone;

    /**
     * Электронная почта
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Перечисление статуса (Enum: Безработный, Работник, Учащийся)
     */
    @Column(name = "employment", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumEmployment employment;

    /**
     * ФИО в дательном падеже
     */
    @Column(name = "fio_dative", nullable = false)
    private String fioDative;

    /**
     * ФИО в родительном падеже
     */
    @Column(name = "fio_genitive", nullable = false)
    private String fioGenitive;

    /**
     * ФИО в именительном падеже
     */
    @Column(name = "fio_nominative", nullable = false)
    private String fioNominative;
}
