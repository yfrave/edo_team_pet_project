package com.education.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.sql.Date;

/**
 * POJO класс, содержащий информацию о датах и работниках.
 * @author Dmitrii Ermolenko
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "resolution")
public class Resolution extends BaseEntity {

    /**
     * Возможные типы решения
     */
    private enum resolutionEnum{
        Резолюция,
        направление,
        запрос
    }

    /**
     * Дата создания резолюции
     */
    @Column(name = "creation_date")
    private Date creationDate;

    /**
     * Дата архивации
     */
    @Column(name = "archived_date")
    private Date archivedDate;

    /**
     * Дата последнего события
     */
    @Column(name = "last_action_date")
    private Date lastActionDate;

    /**
     * Переменная отражающая тип решения
     */
    @Column(name = "resolution_name")
    @Enumerated(EnumType.STRING)
    private resolutionEnum resolutionEnum;

    /**
     * Работник создавший резолюцию
     */
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    private Employee creator;

    /**
     * Работник подписывающий документ
     */
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "signer_id")
    private Employee signer;

    /**
     * Работники выполняющие решение
     */
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "executor_id")
    private List<Employee> executor;

    /**
     * Работник курирующий работу
     */
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "curator_id")
    private Employee curator;
}
