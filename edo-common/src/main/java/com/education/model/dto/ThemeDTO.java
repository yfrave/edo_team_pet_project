package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Класс ThemeDto, DTO для класса Theme.class")
public class ThemeDTO {

    @ApiModelProperty("Название темы обращения")
    private String name;
    @ApiModelProperty("Дата создания темы")
    private Date creationDate;
    @ApiModelProperty("Дата архивации темы")
    private Date archivedDate;
    @ApiModelProperty("Код темы")
    private String code;
    @ApiModelProperty("Родительская тема")
    private ThemeDTO parentTheme;
}
