package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
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
    @Column(name = "first_name", length = 20)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name", length = 20)
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 20)
    private String middleName;

    /**
     * Адрес
     */
    @Column(name = "address", length = 200)
    private String address;

    /**
     * URL-адрес фото
     */
    @Column(name = "photo_url", length = 300)
    private String photoUrl;

    /**
     * ФИО в дательном падеже
     */
    @Column(name = "fio_dative", length = 60)
    private String fioDative;

    /**
     * ФИО в именительном падеже
     */
    @Column(name = "fio_nominative", length = 60)
    private String fioNominative;

    /**
     * ФИО в родительном падеже
     */
    @Column(name = "fio_genitive", length = 60)
    private String fioGenitive;

    /**
     * Внешний идентификатор
     */
    @Column(name = "external_id")
    private String externalId;

    /**
     * Мобильный номер телефона
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Домашний номер телефона
     */
    @Column(name = "work_phone", length = 20)
    private String workPhone;

    /**
     * Дата рождения
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * Имя пользователя
     */
    @Column(name = "username", length = 20)
    private String username;

    /**
     * Дата создания
     */
    @Column(name = "creation_date")
    private Date creationDate;

    /**
     * Дата архивирования
     */
    @Column(name = "archived_date")
    private Date archivedDate;
}
