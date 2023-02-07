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

        Theme parentTheme = theme.getParentTheme();
        ThemeDto parentThemeDto = parentTheme == null
                ? null
                : convertThemeToDto(parentTheme);

        return new ThemeDto(theme.getId(),
                theme.getName(),
                theme.getCreationDate(),
                theme.getArchivedDate(),
                theme.getCode(),
                parentThemeDto);
    }

    /**
     * Метод конвертирует ДТО в тему
     */
    public Theme convertDtoToTheme(ThemeDto themeDto) {
        if (themeDto == null) return null;

        Theme parentTheme = themeDto.getParentThemeDto().getId() == null
                ? null
                : themeRepository.findById(themeDto.getParentThemeDto().getId()).orElse(null);

        return Theme.builder()
                .id(themeDto.getId())
                .name(themeDto.getName())
                .creationDate(themeDto.getCreationDate())
                .archivedDate(themeDto.getArchivedDate())
                .code(themeDto.getCode())
                .parentTheme(parentTheme)
                .build();
    }
}

