package com.education.service.resolution;

import com.education.entity.Resolution;

import java.util.List;

public interface ResolutionService {
    public boolean save(Resolution resolution);
    public boolean moveToArchive(Resolution resolution);
    public Resolution findById(Long id);
    public List<Resolution> findAllById(Iterable<Long> idc);
    public Resolution findByIdNotArchived(Long id);
    public List<Resolution> findAllByIdNotArchived(Iterable<Long> idc);
}
