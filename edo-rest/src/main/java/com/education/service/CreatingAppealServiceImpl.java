package com.education.service;

import com.education.client.CreateAppealRestTemplateClient;
import com.education.model.dto.AppealDto;
import com.education.model.dto.QuestionDto;
import com.education.model.enumEntity.EnumAppealStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatingAppealServiceImpl implements CreatingAppealService {

    private final CreateAppealRestTemplateClient client;
    private final EnumAppealStatus STATUS_FOR_NEW_APPEAL = EnumAppealStatus.NEW;

    @Override
    public AppealDto createAppeal(AppealDto appealDto) {

        //client.saveAuthors(appealDto.getAuthors());
        List<QuestionDto> questions = client.saveQuestion(appealDto.getQuestions());
        appealDto.setQuestions(questions);
        //client.saveFilePool(appealDto.getFile());
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);

        return client.saveAppeal(appealDto);

    }
}