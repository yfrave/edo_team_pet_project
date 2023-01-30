package com.education.util;

import com.education.entity.Appeal;
import com.education.model.dto.AppealDto;

import java.util.ArrayList;
import java.util.List;

public class AppealConverter {

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
                .build();
    }

    public static List<Appeal> dtoToAppeal(List<AppealDto> dtos) {
        List<Appeal> appeal = new ArrayList<>();
        for (AppealDto dto : dtos) {
            appeal.add(dtoToAppeal(dto));
        }
        return appeal;
    }

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
                .build();
    }

    public static List<AppealDto> appealToDto(List<Appeal> appeals) {
        List<AppealDto> dtos = new ArrayList<>();
        for (Appeal appeal : appeals) {
            dtos.add(appealToDto(appeal));
        }
        return dtos;
    }

}
