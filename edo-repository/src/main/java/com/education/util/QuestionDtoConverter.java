package com.education.util;

import com.education.entity.Question;
import com.education.model.dto.QuestionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

/**
 * Класс QuestionDtoConverter,
 * предназначен для конвертации Entity в DTO и наоборот
 */
@Component
public class QuestionDtoConverter {
    /**
     * Метод конвертации для конвертации Entity в DTO
     * @param question
     * @return QuestionDto
     */
    public QuestionDto toDto(Question question) {
        return QuestionDto
                .builder()
                .id(question.getId())
                .creationDate(question.getCreationDate())
                .archivedDate(question.getArchivedDate())
                .summary(question.getSummary())
                .build();
    }

    /**
     * Метод конвертации для конвертации DTO в Entity
     * @param questionDto
     * @return Question
     */
    public Question toEntity(QuestionDto questionDto) {
        return Question
                .builder()
                .id(questionDto.getId())
                .creationDate(questionDto.getCreationDate())
                .archivedDate(questionDto.getArchivedDate())
                .summary(questionDto.getSummary())
                .build();
    }
}
