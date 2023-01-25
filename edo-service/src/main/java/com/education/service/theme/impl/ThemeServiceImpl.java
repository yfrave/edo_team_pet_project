package com.education.service.theme.impl;

import com.education.client.ThemeRestTemplateClient;
import com.education.model.dto.NomenclatureDto;
import com.education.model.dto.ThemeDto;
import com.education.service.theme.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRestTemplateClient client;


    public ThemeDto save(ThemeDto nomenclature) {
        return client.save(nomenclature);
    }

    @Override
    public ThemeDto findById(Long id) {
        return client.findById(id);
    }

}
