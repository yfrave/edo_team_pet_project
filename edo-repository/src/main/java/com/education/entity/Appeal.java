package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
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
    @JoinTable(name = "appeal_employees",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
//    не хватает аннотации
//    без нее работать сохранение и получение данных не будет
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
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "appeal_employees",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    //    не хватает аннотации
//    без нее работать сохранение и получение данных не будет
    //если у тебя будут 2 связки на одну и ту же таблицу, то как хибер должен разграничить, какие из них addressee, а какие signers?
    private List<Employee> addressee;
}

