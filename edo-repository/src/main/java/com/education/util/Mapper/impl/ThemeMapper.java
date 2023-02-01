package com.education.util.Mapper.impl;

import com.education.entity.Theme;
import com.education.model.dto.ThemeDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ThemeMapper extends Mappable<Theme, ThemeDto> {
}
