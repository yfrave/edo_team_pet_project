package com.education.entity;

import com.education.model.dto.AuthorDto;
import com.education.model.enumEntity.EnumEmployment;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Класс сущности автора
 */

@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
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

    /**
     * Конвертация Автора в ДТО
     */
    public static AuthorDto authorToDto(Author author) {
        AuthorDto authorDto = AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .middleName(author.getMiddleName())
                .address(author.getAddress())
                .snils(author.getSnils())
                .mobilePhone(author.getMobilePhone())
                .email(author.getEmail())
                .employment(author.getEmployment())
                .fioDative(author.getFioDative())
                .fioGenitive(author.getFioGenitive())
                .fioNominative(author.getFioNominative())
                .build();

        return authorDto;
    }

    /**
     * Конвертация ДТО в Автора
     */
    public static Author dtoToAuthor(AuthorDto dto) {
        Author author = Author.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .middleName(dto.getMiddleName())
                .address(dto.getAddress())
                .snils(dto.getSnils())
                .mobilePhone(dto.getMobilePhone())
                .email(dto.getEmail())
                .employment(dto.getEmployment())
                .fioDative(dto.getFioDative())
                .fioGenitive(dto.getFioGenitive())
                .fioNominative(dto.getFioNominative())
                .build();

        return author;
    }
}
