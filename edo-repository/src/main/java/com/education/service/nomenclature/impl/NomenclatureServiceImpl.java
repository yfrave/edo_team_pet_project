package com.education.service.nomenclature.impl;

import com.education.converter.NomenclatureToDtoConverter;
import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;
import com.education.repository.NomenclatureRepository;
import com.education.service.nomenclature.NomenclatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {
    private final NomenclatureRepository nomenclatureRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        Nomenclature newNomenclature = NomenclatureToDtoConverter.convertToNomenclature(nomenclature);
        return NomenclatureToDtoConverter.convertToDto(nomenclatureRepository.saveAndFlush(newNomenclature));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        nomenclatureRepository.moveToArchive(id);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public NomenclatureDto findById(Long id) {
        Nomenclature nomenclature = nomenclatureRepository.findById(id).orElse(null);
        return nomenclature != null ? NomenclatureToDtoConverter.convertToDto(nomenclature) : null;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<NomenclatureDto> findAllById(Iterable<Long> list) {
        return nomenclatureRepository.findAllById(list)
                .stream()
                .map(NomenclatureToDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        Nomenclature nomenclature = nomenclatureRepository.findByIdNotArchived(id).orElse(null);
        return nomenclature != null ? NomenclatureToDtoConverter.convertToDto(nomenclature) : null;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(Iterable<Long> list) {
        return nomenclatureRepository.findAllById(list)
                .stream()
                .map(NomenclatureToDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }


}
