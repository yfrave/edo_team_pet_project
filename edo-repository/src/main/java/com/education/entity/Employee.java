package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
 * @author Степан Ритман
 * Класс, описывающий сущность работника
 */
@Table(name = "employee")
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Employee extends BaseEntity {

    /**
     * Имя
     */
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 20, nullable = false)
    private String middleName;

    /**
     * Адрес
     */
    @Column(name = "address", length = 200, nullable = false)
    private String address;

    /**
     * URL-адрес фото
     */
    @Column(name = "photo_url", length = 300, nullable = false)
    private String photoUrl;

    /**
     * ФИО в дательном падеже
     */
    @Column(name = "fio_dative", length = 60, nullable = false)
    private String fioDative;

    /**
     * ФИО в именительном падеже
     */
    @Column(name = "fio_nominative", length = 60, nullable = false)
    private String fioNominative;

    /**
     * ФИО в родительном падеже
     */
    @Column(name = "fio_genitive", length = 60, nullable = false)
    private String fioGenitive;

    /**
     * Внешний идентификатор
     */
    @Column(name = "external_id", nullable = false)
    private String externalId;

    /**
     * Мобильный номер телефона
     */
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    /**
     * Рабочий номер телефона
     */
    @Column(name = "work_phone", length = 20, nullable = false)
    private String workPhone;

    /**
     * Дата рождения
     */
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    /**
     * Имя пользователя
     */
    @Column(name = "username", length = 20, nullable = false)
    private String username;

    /**
     * Дата создания
     */
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    /**
     * Дата архивирования
     */
    @Column(name = "archived_date", nullable = false)
    private Date archivedDate;
}
