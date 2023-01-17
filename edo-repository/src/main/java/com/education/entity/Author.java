package com.education.entity;

import com.education.model.enumEntity.EnumEmployment;
import jakarta.persistence.*;
import lombok.*;

/**
 * Класс сущности автора
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "author")
public class Author extends BaseEntity {

    /**
     * Имя автора
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Фамилия автора
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Отчество автора
     */
    @Column(name = "middle_name", nullable = false)
    private String middleName;

    /**
     * Адрес автора
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * СНИЛС автора
     */
    @Column(name = "snils", nullable = false, length = 11)
    private String snils;

    /**
     * Номер телефона автора
     */
    @Column(name = "mobile_phone", nullable = false, length = 15)
    private String mobilePhone;

    /**
     * Электронная почта автора
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Перечисление статуса (Enum: Безработный, Работник, Учащийся) автора
     */
    @Column(name = "employment", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumEmployment employment;

    /**
     * ФИО автора в дательном падеже
     */
    @Column(name = "fio_dative", nullable = false)
    private String fioDative;

    /**
     * ФИО автора в родительном падеже
     */
    @Column(name = "fio_genitive", nullable = false)
    private String fioGenitive;

    /**
     * ФИО автора в именительном падеже
     */
    @Column(name = "fio_nominative", nullable = false)
    private String fioNominative;
}
