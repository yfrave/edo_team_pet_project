package com.education.util.Mapper.impl;

import com.education.entity.Question;
import com.education.model.dto.QuestionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends Mappable<Question, QuestionDto> {
}
