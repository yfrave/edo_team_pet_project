package com.education.model.dto;

import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToReceive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class AppealWithRelationsDto extends AppealDto {
    private List<AuthorDto> authors;
    private List<FilePoolDto> filePoolDtos;
    private List<QuestionWithRelationsDto> questionDtos;
    private NomenclatureDto nomenclatureDto;
    private EnumAppealStatus appealStatus;
    private EnumWayToReceive wayToReceive;
}
