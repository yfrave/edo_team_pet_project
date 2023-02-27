package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
     * Оповещение пользователя
     */
    @ManyToOne
    @JoinColumn(name = "employeeNotice_id")
    private Employee employeeNotice;

}
