package com.education.service.resolution.impl;

import com.education.entity.Resolution;
import com.education.service.resolution.ResolutionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ResolutionServiceImpl implements ResolutionService {
    /**
     * @param resolution
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(Resolution resolution) {
        return false;
    }

    /**
     * @param resolution
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean moveToArchive(Resolution resolution) {
        return false;
    }

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Resolution findById(Long id) {
        return null;
    }

    /**
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Resolution> findAllById(Iterable<Long> idc) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Resolution findByIdNotArchived(Long id) {
        return null;
    }

    /**
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Resolution> findAllByIdNotArchived(Iterable<Long> idc) {
        return null;
    }
}
