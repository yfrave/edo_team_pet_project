package com.education.service.theme;

import com.education.model.dto.ThemeDto;

public interface ThemeService {

    ThemeDto save(ThemeDto themeDto);

    ThemeDto findById(Long id);

}