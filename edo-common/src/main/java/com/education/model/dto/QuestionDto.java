package com.education.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@ApiModel("Класс QuestionDto - DTO для Question.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class QuestionDto {
    @ApiModelProperty("Id обращения")
    private Long id;

    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата архивирования обращения")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Краткое содержание обращения")
    private String summary;

    @JsonInclude(NON_NULL)
    private ResolutionDto resolution;

    private ThemeDto theme;
}
