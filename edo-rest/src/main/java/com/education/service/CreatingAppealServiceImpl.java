package com.education.service;

import com.education.client.CreateAppealRestTemplateClient;
import com.education.model.dto.AppealDto;
import com.education.model.enumEntity.EnumAppealStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatingAppealServiceImpl implements CreatingAppealService {

    private final CreateAppealRestTemplateClient client;
    private final EnumAppealStatus STATUS_FOR_NEW_APPEAL = EnumAppealStatus.NEW;

    @Override
    public AppealDto createAppeal(AppealDto appealDto) {

        //client.saveAuthors(appealDto.getAuthors());
        client.saveQuestion(appealDto.getQuestions());
        System.out.println(appealDto.getQuestions().get(0).getTheme().getId());
        //client.saveFilePool(appealDto.getFile());
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);

        return client.saveAppeal(appealDto);

    }
}