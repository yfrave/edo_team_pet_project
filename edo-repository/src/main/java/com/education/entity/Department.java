package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * Представляет Департамент, чтобы это не значило.
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
@Table(name = "department")
public class Department extends BaseEntity {
    /**
     * Короткое имя или аббревиатура
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * Полное название
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * Адрес
     */
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    /**
     * Внешний идентификатор
     */
    @Column(name = "need_todo")
    private String needTODO; //externalId (внешний идентификатор, у себя не создаем сущность, скачиваем из чужого хранилища)

    /**
     * Номер телефона
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Вышестоящий департамент
     */
    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department; //department (Связка с вышестоящим департаментом)

    /**
     * Дата создания отдела
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата перевода в архив
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

}
