package com.education.service.theme.impl;

import com.education.entity.Theme;
import com.education.repository.theme.ThemeRepository;
import com.education.service.theme.ThemeService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public void save(Theme theme) {
        themeRepository.save(theme);
    }

    public void moveToArchive(Theme theme) {
        theme.setArchivedDate(ZonedDateTime.now());
//        themeRepository.update(theme);
    }

    public Optional<Theme> findById(Long id) {
        return themeRepository.findById(id);
    }

    public List<Theme> findAllById(List<Long> ids) {
       return themeRepository.findAllById(ids);
    }

    public Theme findByIdNotArchived(Long id) {
        return null;
//        return themeRepository.findByIdNotArchived(id);
    }

    public List<Theme> findAllByIdNotArchived(List<Long> ids) {
        return null;
//        return themeRepository.findAllByIdNotArchived(ids);
    }
}