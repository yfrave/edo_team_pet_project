package com.education.service;

import com.education.client.CreateAppealRestTemplateClient;
import com.education.model.dto.AppealDto;
import com.education.model.dto.AuthorDto;
import com.education.model.dto.FilePoolDto;
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
        List<QuestionDto> questions = client.saveQuestion(appealDto.getQuestion());
        appealDto.setQuestion(questions);
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);
        List<FilePoolDto> file = client.saveFilePool(appealDto.getFile());
        appealDto.setFile(file);
        List<AuthorDto> authors = client.saveAuthors(appealDto.getAuthors());
        appealDto.setAuthors(authors);

        try {
            return client.saveAppeal(appealDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}