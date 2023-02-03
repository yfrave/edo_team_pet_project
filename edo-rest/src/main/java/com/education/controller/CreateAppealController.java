package com.education.controller;

import com.education.service.CreatingAppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rest/create_appeal")
public class CreateAppealController {
    private final CreatingAppealService service;


}
