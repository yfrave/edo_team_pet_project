package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

/**
 * @author Anton Latyshev.
 *
 * Класс Question.
 * Хранит краткое содержание обращений.
 */
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
    private Date creationDate;

    /**
     * Дата архивирования обращения.
     */
    @Column(name = "archived_date", nullable = false)
    private Date archivedDate;

    /**
     * Краткое содержание обращения.
     */
    @Column(nullable = false)
    private String summary;
}
