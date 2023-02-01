package com.education.util.Mapper.impl;

import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface NomenclatureMapper extends Mappable<Nomenclature, NomenclatureDto> {
}
