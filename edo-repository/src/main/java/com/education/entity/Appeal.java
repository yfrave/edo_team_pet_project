package com.education.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;


/**
 * @author Alik Karibov.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "appeal")
/**
 *  Класс "Обращения"
 */
public class Appeal extends BaseEntity {

    /**
     * Дата создания обращения
     */
    @Column(name = "creationDate", nullable = false)
    private ZonedDateTime creationDate;

    /**
     * Дата архивирования обращения
     */
    @Column(name = "archivedDate", nullable = false)
    private ZonedDateTime archivedDate;

    /**
     * Номер обращения
     */
    @Column(name = "number")
    private String number;

    /**
     * Описание обращения
     */
    @Column(name = "annotation", nullable = false)
    private String annotation;

    /**
     * Сотрудники, которые будут подписывать документ
     */

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_signer",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> signer;

    /**
     * Автор
     */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

    /**
     * Получатели
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_addressee",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> addressee;
}

