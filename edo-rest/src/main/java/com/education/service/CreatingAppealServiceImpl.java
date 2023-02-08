package com.education.service;

import com.education.client.CreateAppealRestTemplateClient;
import com.education.model.dto.AppealDto;
import com.education.model.dto.QuestionDto;
import com.education.model.enumEntity.EnumAppealStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        System.out.println("questions before: " + appealDto.getQuestions().get(0).getId());
        List<QuestionDto> questions = client.saveQuestion(appealDto.getQuestions());
//               System.out.println("questions after: " + questions.get(0).getId());
        appealDto.setQuestions(questions);
        //client.saveFilePool(appealDto.getFile());
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);

        try {
            return client.saveAppeal(appealDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}