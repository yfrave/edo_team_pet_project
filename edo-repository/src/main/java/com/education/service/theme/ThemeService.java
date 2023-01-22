package com.education.service.theme;


import com.education.entity.Theme;
import com.education.model.dto.ThemeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThemeService {
    public void save(ThemeDto themeDto);

    public Integer moveToArchive(Long id);

    public ThemeDto findById(Long id);

    public List<ThemeDto> findAllById(List<Long> ids);

    public ThemeDto findByIdNotArchived(Long id);

    public List<ThemeDto> findAllByIdNotArchived(List<Long> ids);
}
