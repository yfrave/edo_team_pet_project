package com.education.service.theme.impl;

import com.education.entity.Theme;
import com.education.model.dto.ThemeDto;
import com.education.repository.theme.ThemeRepository;
import com.education.service.theme.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void save(ThemeDto themeDto) {
        Theme parentTheme = themeDto.getIdParentTheme() == null ? null : themeRepository.findById(themeDto.getIdParentTheme()).orElse(null);
        Theme theme = new Theme(themeDto.getName(), null, themeDto.getArchivedDate(), themeDto.getCode(), parentTheme);
        themeRepository.save(theme);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer moveToArchive(Long id) {
        return themeRepository.moveToArchive(id);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findById(Long id) {
        Theme theme = themeRepository.findById(id).orElse(null);
        if (theme == null) {
            return null;
        } else {
            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
            return new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId);
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllById(List<Long> ids) {
        List<Theme> themes = themeRepository.findAllById(ids);
        List<ThemeDto> listDto = new ArrayList<>();
        for (Theme theme : themes) {
            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
            listDto.add(new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId));
        }
        return listDto;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findByIdNotArchived(Long id) {
        Theme theme = themeRepository.findByIdAndArchivedDateIsNull(id);
        if (theme == null) {
            return null;
        } else {
            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
            return new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId);
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllByIdNotArchived(List<Long> ids) {
        List<Theme> themes = themeRepository.findAllNotArchived(ids);
        List<ThemeDto> listDto = new ArrayList<>();
        for (Theme theme : themes) {
            Long parentThemeId = theme.getParentTheme() != null ? theme.getParentTheme().getId() : null;
            listDto.add(new ThemeDto(theme.getId(), theme.getName(), theme.getCreationDate(), theme.getArchivedDate(), theme.getCode(), parentThemeId));
        }
        return listDto;
    }
}