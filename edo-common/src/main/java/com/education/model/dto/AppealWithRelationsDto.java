package com.education.model.dto;

import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToReceive;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class AppealWithRelationsDto extends AppealDto {
    private List<AuthorDto> authors;
    private List<FilePoolDto> file;
    private List<QuestionWithRelationsDto> questions;
    private NomenclatureDto nomenclature;
    private EnumAppealStatus appealStatus;
    private EnumWayToReceive sendingMethod;

    @JsonCreator
    public AppealWithRelationsDto(Long id, ZonedDateTime creationDate, ZonedDateTime archivedDate, String number, String annotation, @JsonProperty("signers") List<EmployeeDto> signers, EmployeeDto creator, List<EmployeeDto> addressee, List<AuthorDto> authors, List<FilePoolDto> file, List<QuestionWithRelationsDto> questions, NomenclatureDto nomenclature, EnumAppealStatus appealStatus, EnumWayToReceive sendingMethod) {
        super(id, creationDate, archivedDate, number, annotation, signers, creator, addressee);
        this.authors = authors;
        this.file = file;
        this.questions = questions;
        this.nomenclature = nomenclature;
        this.appealStatus = appealStatus;
        this.sendingMethod = sendingMethod;
    }
}
