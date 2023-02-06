package com.education.service;

import com.education.client.CreateAppealRestTemplateClient;
import com.education.model.dto.AppealWithRelationsDto;
import com.education.model.enumEntity.EnumAppealStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatingAppealServiceImpl implements CreatingAppealService {

    private final CreateAppealRestTemplateClient client;
    private final EnumAppealStatus STATUS_FOR_NEW_APPEAL = EnumAppealStatus.NEW;

    @Override
    public AppealWithRelationsDto createAppeal(AppealWithRelationsDto appealDto) {

        client.saveAuthors(appealDto.getAuthors());
        client.saveQuestion(appealDto.getQuestions());
        client.saveFilePool(appealDto.getFile());
        client.saveNomenclature(appealDto.getNomenclature());
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);

        return client.saveAppeal(appealDto);

    }
}