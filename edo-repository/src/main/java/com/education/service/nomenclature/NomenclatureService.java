package com.education.service.nomenclature;

import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;

import java.util.List;
import java.util.Optional;

public interface NomenclatureService {
    NomenclatureDto save(NomenclatureDto nomenclature);
    void moveToArchive(Long id);
    NomenclatureDto findById(Long id);
    List<NomenclatureDto> findAllById(Iterable<Long> list);
    NomenclatureDto findByIdNotArchived(Long id);
    List<NomenclatureDto> findAllByIdNotArchived(Iterable<Long> list);
}
