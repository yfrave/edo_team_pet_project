package com.education.service.theme.impl;

import com.education.converter.ThemeToDtoConverter;
import com.education.entity.Theme;
import com.education.model.dto.ThemeDto;
import com.education.repository.theme.ThemeRepository;
import com.education.service.theme.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для темы обращения
 */
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository THEME_REPOSITORY;
    private final ThemeToDtoConverter CONV;

    /**
     * Получает ThemeDto и сохраняет тему
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ThemeDto save(ThemeDto themeDto) {
        Theme theme = CONV.convertDtoToTheme(themeDto);

        return CONV.convertThemeToDto(THEME_REPOSITORY.save(theme));
    }

    /**
     * Помещает тему с переданным id в архивные, проставляя ей archivedDate
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer moveToArchive(Long id) {
        return THEME_REPOSITORY.moveToArchive(id);
    }

    /**
     * Выдает тему по id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findById(Long id) {
        Theme theme = THEME_REPOSITORY.findById(id).orElse(null);
        if (theme == null) {
            return null;
        } else {
            return CONV.convertThemeToDto(theme);
        }
    }

    /**
     * Выдает темы по списку id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllById(List<Long> ids) {

        return THEME_REPOSITORY.findAllById(ids).stream().map(CONV::convertThemeToDto).toList();
        }

    /**
     * Выдает тему по id, если она не в архивных (т.е. архивная дата отсутствует)
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findByIdNotArchived(Long id) {

        return THEME_REPOSITORY.findByIdAndArchivedDateIsNull(id) == null
                ? null
                : CONV.convertThemeToDto(THEME_REPOSITORY.findByIdAndArchivedDateIsNull(id));
        }

    /**
     * Выдает по списку id соответствующие темы, которые не являются архивными
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllByIdNotArchived(List<Long> ids) {

        return THEME_REPOSITORY.findAllNotArchived(ids).stream().map(CONV::convertThemeToDto).toList();
    }
}