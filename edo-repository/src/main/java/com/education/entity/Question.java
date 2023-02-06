package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * @author Anton Latyshev.
 *
 * Класс Question.
 * Хранит краткое содержание обращений.
 */
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "question")
public class Question extends BaseEntity{
    /**
     * Дата создания обращения.
     */
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    /**
     * Дата архивирования обращения.
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Краткое содержание обращения.
     */
    @Column(nullable = false)
    private String summary;

    /**
     * Объект Resolution, связанный с вопросом
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolution_id")
    private Resolution resolution;

    /**
     * Тема вопроса
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;
}
