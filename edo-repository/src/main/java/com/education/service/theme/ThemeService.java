package com.education.service.theme;


import com.education.entity.Theme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThemeService {
    public void save(Theme theme);

    public Integer moveToArchive(Long id);

    public Theme findById(Long id);

    public List<Theme> findAllById(List<Long> ids);

    public Theme findByIdNotArchived(Long id);

    public List<Theme> findAllByIdNotArchived(List<Long> ids);
}
