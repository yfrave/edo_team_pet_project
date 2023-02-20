package com.education.service.nomenclature.impl;

import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;
import com.education.repository.NomenclatureRepository;
import com.education.service.nomenclature.NomenclatureService;
import com.education.util.Mapper.impl.NomenclatureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Представляет реализацию операций над номенклатурой
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    /**
     * Репозиторий для связи с БД
     */
    private final NomenclatureRepository nomenclatureRepository;

    private final NomenclatureMapper mapper;

    /**
     * Реализует генерацию номера по шаблону 13-12/2022
     * @return StringBuilder
     */
    public StringBuilder dateFormat() {
        Calendar calendar = Calendar.getInstance();
        return new StringBuilder("-" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
    }

    /**
     * Сохраняет номенклатуру в БД
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        Nomenclature newNomenclature = mapper.toEntity(nomenclature);
        newNomenclature.setTemplate(nomenclature.getCurrentValue() + String.valueOf(dateFormat()));
        return mapper.toDto(nomenclatureRepository.saveAndFlush(newNomenclature));
    }

    /**
     * Переводит номенклатуру в архив
     * @param id Long
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        nomenclatureRepository.moveToArchive(id);
    }

    /**
     * Предоставляет NomenclatureDto номенклатуры из БД по id
     * @param id Long
     * @return NomenclatureDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public NomenclatureDto findById(Long id) {
        Nomenclature nomenclature = nomenclatureRepository.findById(id).orElse(null);
        return nomenclature != null ? mapper.toDto(nomenclature) : null;
    }

    /**
     * Предоставляет список NomenclatureDto номенклатур из БД по id
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<NomenclatureDto> findAllById(Iterable<Long> list) {
        List<Nomenclature> nomenclatures = nomenclatureRepository.findAllById(list);
        if (nomenclatures.isEmpty()) {
            return null;
        }
        return mapper.toDto(nomenclatures);
    }

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры из БД по id
     * @param id Long
     * @return NomenclatureDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        Nomenclature nomenclature = nomenclatureRepository.findByIdNotArchived(id).orElse(null);
        return nomenclature != null ? mapper.toDto(nomenclature) : null;
    }

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(Iterable<Long> list) {
        List<Nomenclature> nomenclatures = nomenclatureRepository.findAllByIdNotArchived(list);
        if (nomenclatures.isEmpty()) {
            return null;
        }
        return mapper.toDto(nomenclatures);
    }
}
