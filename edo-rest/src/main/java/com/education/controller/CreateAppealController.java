package com.education.controller;

import com.education.model.dto.AppealWithRelationsDto;
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
    public ResponseEntity<AppealWithRelationsDto> createNewAppeal(@RequestBody String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        AppealWithRelationsDto appealDto = objectMapper.readValue(json, AppealWithRelationsDto.class);
        return new ResponseEntity<>(service.createAppeal(appealDto), HttpStatus.CREATED);
    }
}