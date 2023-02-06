package com.education.model.dto;

import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToReceive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ApiModel("Класс AppealDTO, dto для класса appeal.class")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AppealDto {
    @ApiModelProperty("Id обращения")
    private Long id;

    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата архивирования обращения")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Номер обращения")
    private String number;

    @ApiModelProperty("Описание обращения")
    private String annotation;

    @ApiModelProperty("Подписи")
    private List<EmployeeDto> signer;

    @ApiModelProperty("Автор")
    private EmployeeDto creator;

    @ApiModelProperty("Получатели")
    private List<EmployeeDto> addressee;

    private List<AuthorDto> authors;
    private List<FilePoolDto> file;
    private List<QuestionDto> questions;
    private NomenclatureDto nomenclature;
    private EnumAppealStatus appealStatus;
    private EnumWayToReceive sendingMethod;
}