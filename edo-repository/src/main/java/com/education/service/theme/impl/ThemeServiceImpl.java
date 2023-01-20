package com.education.service.theme.impl;

import com.education.entity.Theme;
import com.education.repository.theme.ThemeRepository;
import com.education.service.theme.ThemeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Transactional
    public void save(Theme theme) {
        themeRepository.save(theme);
    }

    @Transactional
    public Integer moveToArchive(Long id) {
        return themeRepository.moveToArchive(id);
    }

    @Transactional(readOnly = true)
    public Theme findById(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Theme> findAllById(List<Long> ids) {
        return themeRepository.findAllById(ids);
    }

    @Transactional(readOnly = true)
    public Theme findByIdNotArchived(Long id) {
        return themeRepository.findByIdAndArchivedDateIsNull(id);
    }

    @Transactional(readOnly = true)
    public List<Theme> findAllByIdNotArchived(List<Long> ids) {
        return themeRepository.findAllnotArchived(ids);
    }
}