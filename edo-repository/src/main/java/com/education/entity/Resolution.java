package com.education.entity;

import com.education.model.enumEntity.EnumResolution;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * POJO класс, содержащий информацию о датах и работниках.
 * @author Dmitrii Ermolenko
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Table(name = "resolution")
public class Resolution extends BaseEntity {

    /**
     * Дата создания резолюции
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата архивации
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Дата последнего события
     */
    @Column(name = "last_action_date")
    private ZonedDateTime lastActionDate;

    /**
     * Переменная отражающая тип решения
     */
    @Column(name = "resolution_name")
    @Enumerated(EnumType.STRING)
    private EnumResolution enumResolution;

    /**
     * Работник создавший резолюцию
     */
    //@JsonIgnore
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

    /**
     * Работник подписывающий документ
     */
    //@JsonIgnore
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "signer_id")
    private Employee signer;

    /**
     * Работники выполняющие решение
     */
    //@JsonIgnore
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "resolutions_employees",
            joinColumns = @JoinColumn(name = "resolution_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> executors;

    /**
     * Работник курирующий работу
     */
    //@JsonIgnore
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "curator_id")
    private Employee curator;
}
