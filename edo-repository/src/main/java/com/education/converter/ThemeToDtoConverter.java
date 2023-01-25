package com.education.converter;

import com.education.entity.Theme;
import com.education.model.dto.ThemeDto;
import com.education.repository.theme.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Преобразование темы -> ThemeDTO и в обратную сторону
 */
@Component
@RequiredArgsConstructor
public class ThemeToDtoConverter {

    private final ThemeRepository themeRepository;

    /**
     * Конвертирует тему в ThemeDTO
     */
    public ThemeDto convertThemeToDto(Theme theme) {

        Long parentThemeId = theme.getParentTheme() == null
                ? null
                : theme.getParentTheme().getId();

        return new ThemeDto(theme.getId(),
                theme.getName(),
                theme.getCreationDate(),
                theme.getArchivedDate(),
                theme.getCode(),
                parentThemeId);
    }

    /**
     * Метод конвертирует ДТО в тему
     */
    public Theme convertDtoToTheme(ThemeDto themeDto) {

        Theme parentTheme = themeDto.getIdParentTheme() == null
                ? null
                : themeRepository.findById(themeDto.getIdParentTheme()).orElse(null);

        return new Theme(themeDto.getName(),
                themeDto.getCreationDate(),
                themeDto.getArchivedDate(),
                themeDto.getCode(),
                parentTheme);
    }
}
