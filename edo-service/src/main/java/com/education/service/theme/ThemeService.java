package com.education.service.theme;

import com.education.model.dto.ThemeDto;
import com.education.model.dto.ThemeDto;

import java.util.List;

public interface ThemeService {

    ThemeDto save(ThemeDto themeDto);

    ThemeDto findById(Long id);

    List<ThemeDto> findAllById(List<Long> list);
    
    ThemeDto findByIdNotArchived(Long id);
    
    List<ThemeDto> findAllByIdNotArchived(List<Long> list);

    void moveToArchive(Long id);
}