package com.education.converter;

import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;

/**
 * Представляет конвертер номенклатуры в ДТО
 * и ДТО в номенклатуру
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

public class NomenclatureToDtoConverter {

    /**
     * Метод конвертирует номенклатуру в ДТО
     * @param nomenclature Nomenclature
     * @return NomenclatureDto
     */
    public static NomenclatureDto convertToDto(Nomenclature nomenclature) {
        return new NomenclatureDto(nomenclature.getId(),
                nomenclature.getCreationDate(),
                nomenclature.getArchivedDate(),
                nomenclature.getTemplate(),
                nomenclature.getCurrentValue(),
                nomenclature.getIndex());
    }

    /**
     * Метод конвертирует ДТО в номенклатуру
     * @param nomenclatureDto NomenclatureDto
     * @return Nomenclature
     */
    public static Nomenclature convertToNomenclature(NomenclatureDto nomenclatureDto) {
        return new Nomenclature(nomenclatureDto.getCreationDate(),
                nomenclatureDto.getArchivedDate(),
                nomenclatureDto.getTemplate(),
                nomenclatureDto.getCurrentValue(),
                nomenclatureDto.getIndex());
    }
}
