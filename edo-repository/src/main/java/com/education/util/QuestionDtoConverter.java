package com.education.util;

import com.education.converter.ThemeToDtoConverter;
import com.education.entity.Question;
import com.education.model.dto.QuestionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Класс QuestionDtoConverter,
 * предназначен для конвертации Entity в DTO и наоборот
 */
@Component
@RequiredArgsConstructor
public class QuestionDtoConverter {
    private final ThemeToDtoConverter themeConverter;
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
                .theme(themeConverter.convertThemeToDto(question.getTheme()))
                .resolution(ResolutionConverter.resolutionToDto(question.getResolution()))
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
                .theme(themeConverter.convertDtoToTheme(questionDto.getTheme()))
                .resolution(questionDto.getResolution() == null
                        || questionDto.getResolution().getId() == null
                        ? null
                        : ResolutionConverter.dtoToResolution(questionDto.getResolution()))
                .build();
    }
}
