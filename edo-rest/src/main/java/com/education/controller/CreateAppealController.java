package com.education.controller;

import com.education.model.dto.AppealWithRelationsDto;
import com.education.service.CreatingAppealService;
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

    @PostMapping
    private void createAppealService(@RequestBody AppealWithRelationsDto json) {
        service.createAppeal(json);
    }

}
