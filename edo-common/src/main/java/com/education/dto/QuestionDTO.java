package com.education.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Date;

@ApiModel("Класс QuestionDTO - DTO для Question.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    @ApiModelProperty("Id обращения")
    private long id;

    @ApiModelProperty("Дата создания обращения")
    private Date creationDate;

    @ApiModelProperty("Дата архивирования обращения")
    private Date archivedDate;

    @ApiModelProperty("Краткое содержание обращения")
    private String summary;
}
