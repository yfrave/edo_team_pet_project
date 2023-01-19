package com.education.service.resolution.impl;

import com.education.entity.Resolution;
import com.education.repository.ResolutionRepository;
import com.education.service.resolution.ResolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    final ResolutionRepository resolutionRepository;


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
