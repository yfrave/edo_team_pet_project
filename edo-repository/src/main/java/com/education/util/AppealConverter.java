package com.education.util;

import com.education.converter.NomenclatureToDtoConverter;
import com.education.entity.Appeal;
import com.education.model.dto.AppealDto;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AppealConverter {
    private static QuestionDtoConverter questionConverter;

    //TODO author field, filepool field
    public static Appeal dtoToAppeal(AppealDto dto) {
        return Appeal.builder()
                .id(dto.getId())
                .creationDate(dto.getCreationDate())
                .archivedDate(dto.getArchivedDate())
                .number(dto.getNumber())
                .annotation(dto.getAnnotation())
                .signer(EmployeeConverter
                        .dtoToEmployee(dto.getSigner()))
                .creator(EmployeeConverter
                        .dtoToEmployee(dto.getCreator()))
                .addressee(EmployeeConverter
                        .dtoToEmployee(dto.getAddressee()))
                .question(dto
                        .getQuestions()
                        .stream()
                        .map(questionConverter::toEntity)
                        .collect(Collectors.toList()))
                .nomenclature(NomenclatureToDtoConverter
                        .convertToNomenclature(dto.getNomenclature()))
                .build();
    }

    public static List<Appeal> dtoToAppeal(List<AppealDto> dtos) {
        return dtos.stream()
                .map(AppealConverter::dtoToAppeal)
                .collect(Collectors.toList());
    }

    //TODO author field, filepool field
    public static AppealDto appealToDto(Appeal appeal) {
        return AppealDto.builder()
                .id(appeal.getId())
                .creationDate(appeal.getCreationDate())
                .archivedDate(appeal.getArchivedDate())
                .number(appeal.getNumber())
                .annotation(appeal.getAnnotation())
                .signer(EmployeeConverter
                        .employeeToDto(appeal.getSigner()))
                .creator(EmployeeConverter
                        .employeeToDto(appeal.getCreator()))
                .addressee(EmployeeConverter
                        .employeeToDto(appeal.getAddressee()))
                .questions(appeal
                        .getQuestion()
                        .stream()
                        .map(questionConverter::toDto)
                        .collect(Collectors.toList()))
                .nomenclature(NomenclatureToDtoConverter
                        .convertToDto(appeal.getNomenclature()))
                .build();
    }

    public static List<AppealDto> appealToDto(List<Appeal> appeals) {
        return appeals.stream()
                .map(AppealConverter::appealToDto)
                .collect(Collectors.toList());
    }


}
