package com.education.util.Mapper.impl;

import com.education.entity.Resolution;
import com.education.model.dto.ResolutionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResolutionMapper extends Mappable<Resolution, ResolutionDto> {
}
