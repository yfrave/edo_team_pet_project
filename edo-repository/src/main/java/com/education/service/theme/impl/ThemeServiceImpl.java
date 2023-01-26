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

/**
 * Сервис для темы обращения
 */
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeToDtoConverter conv;


    /**
     * Получает ThemeDto и сохраняет тему
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ThemeDto save(ThemeDto themeDto) {
        Theme theme = conv.convertDtoToTheme(themeDto);

//        Theme parentTheme = themeDto.getIdParentTheme() == null ? null : themeRepository.findById(themeDto.getIdParentTheme()).orElse(null);
//        Theme theme = new Theme(themeDto.getName(), null, themeDto.getArchivedDate(), themeDto.getCode(), parentTheme);

        return conv.convertThemeToDto(themeRepository.save(theme));
    }

    /**
     * Помещает тему с переданным id в архивные, проставляя ей archivedDate
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer moveToArchive(Long id) {
        return themeRepository.moveToArchive(id);
    }

    /**
     * Выдает тему по id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findById(Long id) {
        Theme theme = themeRepository.findById(id).orElse(null);
        if (theme == null) {
            return null;
        } else {
            return conv.convertThemeToDto(theme);

//            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
//            return new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId);
        }
    }

    /**
     * Выдает темы по списку id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllById(List<Long> ids) {
        List<Theme> themes = themeRepository.findAllById(ids);
        List<ThemeDto> listDto = new ArrayList<>();
        for (Theme theme : themes) {
//            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
//            listDto.add(new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId));
            listDto.add(conv.convertThemeToDto(theme));

        }
        return listDto;
    }

    /**
     * Выдает тему по id, если она не в архивных (т.е. архивная дата отсутствует)
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findByIdNotArchived(Long id) {
        Theme theme = themeRepository.findByIdAndArchivedDateIsNull(id);
        if (theme == null) {
            return null;
        } else {
            return conv.convertThemeToDto(theme);

//            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
//            return new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId);
        }
    }

    /**
     * Выдает по списку id соответствующие темы, которые не являются архивными
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllByIdNotArchived(List<Long> ids) {
        List<Theme> themes = themeRepository.findAllNotArchived(ids);
        List<ThemeDto> listDto = new ArrayList<>();
        for (Theme theme : themes) {
//            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
//            listDto.add(new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId));
            listDto.add(conv.convertThemeToDto(theme));
        }
        return listDto;
    }
}