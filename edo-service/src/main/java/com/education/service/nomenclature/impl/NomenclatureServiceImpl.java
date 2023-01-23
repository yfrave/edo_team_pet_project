package com.education.service.nomenclature.impl;

import com.education.client.NomenclatureRestTemplateClient;
import com.education.model.dto.NomenclatureDto;
import com.education.service.nomenclature.NomenclatureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {
    private final NomenclatureRestTemplateClient client;

    @Override
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        return client.save(nomenclature);
    }

    @Override
    public void moveToArchive(Long id) {
        client.moveToArchive(id);
    }

    @Override
    public NomenclatureDto findById(Long id) {
        return client.findById(id);
    }

    @Override
    public List<NomenclatureDto> findAllById(List<Long> list) {
        return client.findAllById(list);
    }

    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        return client.findByIdNotArchived(id);
    }

    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> list) {
        return client.findAllByIdNotArchived(list);
    }
}
