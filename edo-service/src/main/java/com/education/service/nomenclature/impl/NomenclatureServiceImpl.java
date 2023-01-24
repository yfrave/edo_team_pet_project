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
     * принимает объект NomenclatureDto
     */
    @Override
    public void save(NomenclatureDto nomenclature) {
        client.save(nomenclature);
    }

    /**
     * Переводит номенклатуру в архив
     * принимает id номенклатуры, которую надо отправить в архив
     */
    @Override
    public void moveToArchive(Long id) {
        client.moveToArchive(id);
    }

    /**
     * Предоставляет NomenclatureDto номенклатуры по id
     * принимает id искомой номенклатуру
     */
    @Override
    public NomenclatureDto findById(Long id) {
        return client.findById(id);
    }

    /**
     * Предоставляет список NomenclatureDto номенклатур по id
     * принимает список id искомых номенклатур
     */
    @Override
    public List<NomenclatureDto> findAllById(List<Long> list) {
        return client.findAllById(list);
    }

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры по id
     * принимает id искомой номенклатуру
     */
    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        return client.findByIdNotArchived(id);
    }

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур по id
     * принимает список id искомых номенклатуру
     */
    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> list) {
        return client.findAllByIdNotArchived(list);
    }
}
