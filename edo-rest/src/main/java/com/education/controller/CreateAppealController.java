package com.education.controller;

import com.education.model.dto.AppealDto;
import com.education.model.dto.QuestionDto;
import com.education.service.CreatingAppealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rest/create_appeal")
public class CreateAppealController {

    private final CreatingAppealService service;

    @PostMapping("/")
    public ResponseEntity<AppealDto> createNewAppeal(@RequestBody String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        AppealDto appealDto = objectMapper.readValue(json, AppealDto.class);
        service.createAppeal(appealDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}