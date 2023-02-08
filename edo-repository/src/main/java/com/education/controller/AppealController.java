package com.education.controller;

import com.education.model.dto.AppealDto;
import com.education.service.appeal.AppealService;
import com.education.util.AppealConverter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.education.entity.Appeal;


import java.util.List;

@ApiOperation("Appeal API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/repository/appeal")
public class AppealController {
    final private AppealService appealService;


    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<AppealDto> saveAppeal(@RequestBody Appeal appeal) {
        System.out.println(appeal.getId());
        System.out.println(appeal.getQuestion());
        appealService.save(appeal);
        System.out.println(appeal.getId());
        System.out.println(appeal.getQuestion());
        if (appealService.findById(appeal.getId()) != null) {
            log.log(Level.INFO, "Сущность сохранена или обновлена");
            return new ResponseEntity<>(AppealConverter.appealToDto(appeal), HttpStatus.CREATED);
        }
        log.log(Level.WARN, "Сущность не сохранена и не обновлена");
        return new ResponseEntity<>(AppealConverter.appealToDto(appeal), HttpStatus.CONFLICT);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/toArchive/{id}")
    public ResponseEntity<AppealDto> moveToArchiveAppeal(@PathVariable Long id) {
        appealService.moveToArchive(id);
        log.log(Level.INFO, "Дата архивации обновлена");
        return new ResponseEntity<>(AppealConverter.appealToDto(appealService.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<AppealDto> findByIdAppeal(@PathVariable Long id) {
        Appeal appeal = appealService.findById(id);
        if (appeal == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(AppealConverter.appealToDto(appeal), HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по списку id (/1, 2, 3)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allById/{ids}")
    public ResponseEntity<List<AppealDto>> findAllByIdAppeal(@PathVariable List<Long> ids) {
        List<Appeal> appeal = appealService.findAllById(ids);
        if (appeal == null && appeal.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(AppealConverter.appealToDto(appeal), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<AppealDto> findByIdNotArchivedAppeal(@PathVariable Long id) {
        Appeal appeal = appealService.findByIdNotArchived(id);
        if (appeal == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(AppealConverter.appealToDto(appeal), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allNotArchived/{ids}")
    public ResponseEntity<List<AppealDto>> findAllByIdNotArchivedAppeal(@PathVariable List<Long> ids) {
        List<Appeal> appeal = appealService.findAllByIdNotArchived(ids);
        if (appeal == null && appeal.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(AppealConverter.appealToDto(appeal), HttpStatus.OK);
    }
}
