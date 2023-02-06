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

    @Override
    public AppealWithRelationsDto createAppeal(AppealWithRelationsDto appealDto) {

        client.saveAuthor(appealDto.getAuthors());
        client.saveQuestion(appealDto.getQuestions());
        client.saveFilePool(appealDto.getFile());
        appealDto.setAppealStatus(EnumAppealStatus.NEW);
        client.saveAppeal(appealDto);

        return client.getAppeal();

    }
}
