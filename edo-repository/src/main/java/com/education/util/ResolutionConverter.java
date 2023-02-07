package com.education.util;

import com.education.entity.Resolution;
import com.education.model.dto.ResolutionDto;

import java.util.ArrayList;
import java.util.List;

public class ResolutionConverter {

    public static Resolution dtoToResolution(ResolutionDto dto) {
        if(dto.getId() == null) return null;
        return Resolution.builder()
                .id(dto.getId())
                .creationDate(dto.getCreationDate())
                .archivedDate(dto.getArchivedDate())
                .lastActionDate(dto.getLastActionDate())
                .enumResolution(dto.getEnumResolution())
                .creator(EmployeeConverter
                        .dtoToEmployee(dto.getCreator()))
                .signer(EmployeeConverter
                        .dtoToEmployee(dto.getSigner()))
                .executors(EmployeeConverter
                        .dtoToEmployee(dto.getExecutors()))
                .curator(EmployeeConverter
                        .dtoToEmployee(dto.getCurator()))
                .build();
    }
    public static List<Resolution> dtoToResolution(List<ResolutionDto> dtos) {
        List<Resolution> resolutions=new ArrayList<>();
        for (ResolutionDto dto : dtos) {
            resolutions.add(dtoToResolution(dto));
        }
        return resolutions;
    }

    public static ResolutionDto resolutionToDto(Resolution resolution) {
        return ResolutionDto.builder()
                .id(resolution.getId())
                .creationDate(resolution.getCreationDate())
                .archivedDate(resolution.getArchivedDate())
                .lastActionDate(resolution.getLastActionDate())
                .enumResolution(resolution.getEnumResolution())
                .creator(EmployeeConverter
                        .employeeToDto(resolution.getCreator()))
                .signer(EmployeeConverter
                        .employeeToDto(resolution.getSigner()))
                .executors(EmployeeConverter
                        .employeeToDto(resolution.getExecutors()))
                .curator(EmployeeConverter
                        .employeeToDto(resolution.getCurator()))
                .build();
    }
    public static List<ResolutionDto> resolutionToDto(List<Resolution> resolutions) {
        List<ResolutionDto> dtos=new ArrayList<>();
        for (Resolution resolution : resolutions) {
            dtos.add(resolutionToDto(resolution));
        }
        return dtos;
    }

}
