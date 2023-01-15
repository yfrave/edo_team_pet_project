package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.ZonedDateTime;

/**
 * Представляет тему обращения.
 *
 * @author Алексей Сементковский
 * @version 1.0
 * @since 1.0
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@Table(name = "theme")
public class Theme extends BaseEntity {

    /**
     * Имя темы обращения
     */
    @Column(name = "theme_name")
    private String name;

    /**
     * Дата создания
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата перемещения в архив
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Код темы
     */
    @Column(name = "code")
    private String code;

    /**
     * Родительская тема
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Theme parentTheme;
}