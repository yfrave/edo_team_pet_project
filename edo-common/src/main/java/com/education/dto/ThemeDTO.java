package com.education.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Представляет DTO темы обращения.
 *
 * @author Алексей Сементковский
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThemeDTO {

    private String name;
    private Date creationDate;
    private Date archivedDate;
    private Long code;
    private ThemeDTO parentTheme;
}
