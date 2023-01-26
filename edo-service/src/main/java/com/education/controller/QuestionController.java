package com.education.controller;

import com.education.model.dto.QuestionDto;
import com.education.service.question.QuestionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiModel("Класс QuestionController - RestController для взаимодействия с обращениями")
@RestController
@AllArgsConstructor
@RequestMapping("/api/service/question")
public class QuestionController {
    @ApiModelProperty("questionService")
    private final QuestionService questionService;

    @ApiOperation("Создание обращения")
    @PostMapping("/add")
    public ResponseEntity<QuestionDto> save(@RequestBody QuestionDto question) {
        questionService.save(question);
        return question != null
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation("Перемещение обращения в архив")
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> moveToArchive(@PathVariable("id") Long id) {
        questionService.moveToArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Поиск обращения по id")
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findById(@PathVariable("id") Long id) {
        QuestionDto questionDto = questionService.findById(id);
        return questionDto != null
                ? new ResponseEntity<>(questionDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Поиск списка обращений по их id")
    @PostMapping("/all")
    public ResponseEntity<List<QuestionDto>> findAllById(@RequestBody List<Long> ids) {
        List<QuestionDto> questionsDto = questionService.findAllById(ids);
        return questionsDto != null && !questionsDto.isEmpty()
                ? new ResponseEntity<>(questionsDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Поиск неархивированного обращения по id")
    @GetMapping("/notArchived/{id}")
    public ResponseEntity<QuestionDto> findByIdNotArchived(@PathVariable("id") Long id) {
        QuestionDto questionDto = questionService.findByIdNotArchived(id);
        return questionDto != null
                ? new ResponseEntity<>(questionDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Поиск списка неархивированных обращений по их id")
    @PostMapping("/notArchivedAll")
    public ResponseEntity<List<QuestionDto>> findAllByIdNotArchived(@RequestBody List<Long> ids) {
        List<QuestionDto> questions = questionService.findAllByIdNotArchived(ids);
        return questions != null && !questions.isEmpty()
                ? new ResponseEntity<>(questions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
