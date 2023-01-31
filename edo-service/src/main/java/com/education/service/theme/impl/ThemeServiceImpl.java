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

    private final ThemeRestTemplateClient client;


    public ThemeDto save(ThemeDto nomenclature) {
        return client.save(nomenclature);
    }

    public ThemeDto findById(Long id) {
        return client.findById(id);
    }

    public List<ThemeDto> findAllById(List<Long> list) {
        return client.findAllById(list);
    }

    public ThemeDto findByIdNotArchived(Long id) {
        return client.findByIdNotArchived(id);
    }
    
    public List<ThemeDto> findAllByIdNotArchived(List<Long> list) {
        return client.findAllByIdNotArchived(list);
    }
    
    public void moveToArchive(Long id) {
        client.moveToArchive(id);
    }
}