package com.education.converter;

import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;
import org.springframework.stereotype.Component;

@Component
public class NomenclatureToDtoConverter {
    public static NomenclatureDto convertToDto(Nomenclature nomenclature) {
        return new NomenclatureDto(nomenclature.getId(),
                nomenclature.getCreationDate(),
                nomenclature.getArchivedDate(),
                nomenclature.getTemplate(),
                nomenclature.getCurrentValue(),
                nomenclature.getIndex());
    }
    public static Nomenclature convertToNomenclature(NomenclatureDto nomenclatureDto) {
        return new Nomenclature(nomenclatureDto.getCreationDate(),
                nomenclatureDto.getArchivedDate(),
                nomenclatureDto.getTemplate(),
                nomenclatureDto.getCurrentValue(),
                nomenclatureDto.getIndex());
    }
}
