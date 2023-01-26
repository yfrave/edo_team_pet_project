package com.education.service.theme.impl;

import com.education.client.ThemeRestTemplateClient;
import com.education.model.dto.ThemeDto;
import com.education.service.theme.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRestTemplateClient CLIENT;


    public ThemeDto save(ThemeDto nomenclature) {
        return CLIENT.save(nomenclature);
    }

    public ThemeDto findById(Long id) {
        return CLIENT.findById(id);
    }

    public List<ThemeDto> findAllById(List<Long> list) {
        return CLIENT.findAllById(list);
    }

    public ThemeDto findByIdNotArchived(Long id) {
        return CLIENT.findByIdNotArchived(id);
    }
    
    public List<ThemeDto> findAllByIdNotArchived(List<Long> list) {
        return CLIENT.findAllByIdNotArchived(list);
    }
    
    public void moveToArchive(Long id) {
        CLIENT.moveToArchive(id);
    }
}