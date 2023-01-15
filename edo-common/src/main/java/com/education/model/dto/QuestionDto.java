package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Date;
import java.time.ZonedDateTime;

@ApiModel("Класс QuestionDto - DTO для Question.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto {
    @ApiModelProperty("Id обращения")
    private Long id;

    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата архивирования обращения")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Краткое содержание обращения")
    private String summary;
}
