package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "nomenclature")
public class Nomenclature extends BaseEntity {

    /**
     * Дата создания номеклатуры
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата перевода в архив
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Шаблон
     */
    @Column(name = "template")
    private String template;

    /**
     * Текущее значение
     */
    @Column(name = "current_value")
    private Long currentValue;

    /**
     * Индекс
     */
    @Column(name = "index")
    private String index;
}
