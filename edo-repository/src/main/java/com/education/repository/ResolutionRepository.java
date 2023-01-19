package com.education.repository;

import com.education.entity.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    public boolean moveToArchive(Resolution resolution);
    public Resolution findByIdNotArchived(Long id);
    public Resolution findAllByIdNotArchived(Iterable<Long> idc);
}
