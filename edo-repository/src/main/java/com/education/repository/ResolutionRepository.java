package com.education.repository;

import com.education.entity.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    public boolean moveToArchive(Resolution resolution);
    public Resolution findByIdNotArchived(Long id);
    public Resolution findAllByIdNotArchived(Iterable<Long> idc);
}
