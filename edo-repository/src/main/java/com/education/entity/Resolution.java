package com.education.entity;

import jakarta.persistence.*;

import java.util.List;
import java.sql.Date;

/**
 * POJO класс, содержащий информацию о датах и работниках.
 * @author Dmitrii Ermolenko
 */
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
    @Column(name = "creator")
    private Employee creator;

    /**
     * Работник подписывающий документ
     */
    @Column(name = "signer")
    private Employee signer;

    /**
     * Работники выполняющие решение
     */
    @Column(name = "executor")
    private List<Employee> executor;

    /**
     * Работник курирующий работу
     */
    @Column(name = "curator")
    private Employee curator;
}
