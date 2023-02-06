package com.education.controller;

import com.education.model.dto.AppealWithRelationsDto;
import com.education.service.CreatingAppealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
    public void theMethod(@RequestBody String json) throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();

// Deserialization into the `Employee` class
        AppealWithRelationsDto appealDto = objectMapper.readValue(json, AppealWithRelationsDto.class);


//        UserStory:
//        Пользователь заполняет Подписанта, Адресатов, выбирает номенклатуру,
//        заполняет данные автора обращения, заполняет аннотацию, создает новый вопрос(ы),
//        которые поднял автор: выбирает тему вопроса, заполняет краткое содержание,
//        загружает файл, нажимает кнопку сохранить и все его данные успешно сохраняются в бд

        System.out.println(appealDto.getSendingMethod());
    }

}


