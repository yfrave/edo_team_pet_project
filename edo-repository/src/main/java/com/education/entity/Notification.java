package com.education.entity;

import com.education.model.enumEntity.EnumNotification;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * @author Хафизов Ильмир
 * Класс, описывающий оповещения пользователя
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@EqualsAndHashCode
@Table(name = "notification")
public class Notification extends BaseEntity {

    /**
     * Тип оповещения
     */
    @Column(name = "notification_name")
    @Enumerated(EnumType.STRING)
    private EnumNotification enumNotification;
    /**
     * Связь с таблицей employee
     */
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    /**
     * Сообщение
     */
    @Column(name = "message")
    private String message;

    /**
     * Дата создания нотификации
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
}
