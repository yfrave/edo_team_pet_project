package com.education.service.nomenclature.impl;

import com.education.client.NomenclatureRestTemplateClient;
import com.education.model.dto.NomenclatureDto;
import com.education.service.nomenclature.NomenclatureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Представляет реализацию операций над номенклатурой
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

@Service
@AllArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    /**
     * Клиент для связи с модулем edo-repository
     */
    private final NomenclatureRestTemplateClient client;

    /**
     * Сохраняет номенклатуру
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        return client.save(nomenclature);
    }

    /**
     * Переводит номенклатуру в архив
     * @param id Long
     */
    @Override
    public void moveToArchive(Long id) {
        client.moveToArchive(id);
    }

    /**
     * Предоставляет NomenclatureDto номенклатуры по id
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findById(Long id) {
        return client.findById(id);
    }

    /**
     * Предоставляет список NomenclatureDto номенклатур по id
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllById(List<Long> list) {
        return client.findAllById(list);
    }

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры по id
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        return client.findByIdNotArchived(id);
    }

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> list) {
        return client.findAllByIdNotArchived(list);
    }
}
