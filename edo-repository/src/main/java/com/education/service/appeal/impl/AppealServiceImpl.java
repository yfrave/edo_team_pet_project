package com.education.service.appeal.impl;

import com.education.entity.Appeal;
import com.education.repository.AppealRepository;
import com.education.service.appeal.AppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

    final AppealRepository appealRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Appeal appeal) {
        appealRepository.save(appeal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        appealRepository.moveToArchive(id);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findById(Long id) {
        return appealRepository.findById(id).get();
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllById(Iterable<Long> ids) {
        return appealRepository.findAllById(ids);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findByIdNotArchived(Long id) {
        return appealRepository.findByIdNotArchived(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllByIdNotArchived(Iterable<Long> ids) {
        return appealRepository.findAllByIdNotArchived(ids);

    }
}
