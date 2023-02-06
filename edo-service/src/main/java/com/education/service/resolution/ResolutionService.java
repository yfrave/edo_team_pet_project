package com.education.service.resolution;

import com.education.model.dto.ResolutionDto;

import java.util.List;

public interface ResolutionService {
    void save(ResolutionDto resolution);

    void moveToArchive(Long id);

    ResolutionDto findById(Long id);

    List<ResolutionDto> findAllById(Iterable<Long> ids);

    ResolutionDto findByIdNotArchived(Long id);

    List<ResolutionDto> findAllByIdNotArchived(Iterable<Long> ids);
}
