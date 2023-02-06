package com.education.service;

import com.education.model.dto.AppealWithRelationsDto;
import org.springframework.stereotype.Service;

@Service
public class CreatingAppealServiceImpl implements CreatingAppealService {

    @Override
    public AppealWithRelationsDto createAppeal(AppealWithRelationsDto appealDto) {
        return appealDto;

    }
}
