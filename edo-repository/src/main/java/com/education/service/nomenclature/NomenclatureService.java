package com.education.service.nomenclature;

import com.education.model.dto.NomenclatureDto;

import java.util.List;

/**
 * Представляет список операций над номенклатурой
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

public interface NomenclatureService {

    /**
     * Сохраняет номенклатуру в БД
     * принимает объект NomenclatureDto
     */
    void save(NomenclatureDto nomenclature);

    /**
     * Переводит номенклатуру в архив
     * принимает id номенклатуры, которую надо отправить в архив
     */
    void moveToArchive(Long id);

    /**
     * Предоставляет NomenclatureDto номенклатуры из БД по id
     * принимает id искомой номенклатуру
     */
    NomenclatureDto findById(Long id);

    /**
     * Предоставляет список NomenclatureDto номенклатур из БД по id
     * принимает список id искомых номенклатур
     */
    List<NomenclatureDto> findAllById(Iterable<Long> list);

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры из БД по id
     * принимает id искомой номенклатуру
     */
    NomenclatureDto findByIdNotArchived(Long id);

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     * принимает список id искомых номенклатуру
     */
    List<NomenclatureDto> findAllByIdNotArchived(Iterable<Long> list);
}
