package com.education.controller;


import com.education.model.dto.FilePoolDto;
import com.education.service.FilePoolService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service/file_pool")
@AllArgsConstructor
@ApiModel("Контроллер эдо-сервиса для сущности Хранилище файлов (FilePool)")
@Log
public class FilePoolController {
    @ApiModelProperty("Сервис контроллера FilePool")
    private FilePoolService service;

    @ApiOperation(value = "Получить хранилище файлов по id", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The file pool was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FilePoolDto> getFilePool(@PathVariable Long id) {
        log.info("Got request to find file pool by id = " + id);
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список файловых хранилищ", notes = "Находит файловые хранилища по их id. Возвращает списком List<FilePool>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The file pools was not found")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<FilePoolDto>> getListOfFilePoolByIds(@RequestParam List<Long> ids) {
        log.info("Got request to find list of file pool");
        return new ResponseEntity<>(service.findAllById(ids), HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить хранилище файлов")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added")
    })
    @PostMapping("/")
    public ResponseEntity<FilePoolDto> add(@RequestBody @ApiParam("FilePool") FilePoolDto filePoolDto) {
        log.info("Got request to add new file pool");
        return new ResponseEntity<>(service.add(filePoolDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Переместить в архив хранилище файлов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully moved")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> moveToArchive(@PathVariable @ApiParam("FilePool") Long id) {
        log.info("Got request to move file pool to archive by id = " + id);
        service.moveToArchive(id);
        return ResponseEntity.ok().build();
    }

}
