package com.education.controller;

import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.service.filepool.FilePoolService;
import com.education.util.Mapper.impl.FilePoolMapper;
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
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Level;

@RestController
@Log
@RequestMapping("/api/repository/file_pool")
@AllArgsConstructor
@ApiModel("Controller for FilePool")
public class FilePoolController {
    @ApiModelProperty("service")
    private final FilePoolService repository;

    @ApiModelProperty("mapper")
    private final FilePoolMapper mapper;


    @ApiOperation(value = "Получить хранилище файлов по id", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The file pool was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FilePoolDto> fetchFilePool(@PathVariable("id") Long id) {
        log.info("Request to get file pool by id = " + id);
        FilePoolDto filePoolDto = repository.findById(id);
        return filePoolDto != null
                ? new ResponseEntity<>(filePoolDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Получить список файловых хранилищ", notes = "Находит файловые хранилища по их id. Возвращает списком List<FilePool>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The file pools was not found")
    })
    @PostMapping("/findAll")
    public ResponseEntity<List<FilePoolDto>> fetchFindAllById(@RequestBody
                                                              List<Long> ids) {
        log.info("Got request to get file pools by ids");
        List<FilePoolDto> filePools = repository.findAllById(ids);
        if (filePools == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return !filePools.isEmpty()
                ? new ResponseEntity<>(filePools, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Добавить хранилище файлов")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added")
    })
    @PostMapping("/")
    public ResponseEntity<FilePoolDto> add(@RequestBody
                                           @ApiParam("filePool")
                                           FilePool filePool) {

        filePool.setUploadDate(ZonedDateTime.now());
        log.info("Got request to add new file pool");
        ResponseEntity<FilePoolDto> responseEntity = new ResponseEntity<>(mapper.toDto(repository.add(filePool)), HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value = "Переместить в архив хранилище файлов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully moved")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> moveToArchived(@PathVariable("id") Long id) {
        log.info("Got request to move file pool to archive");
        repository.moveToArchive(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Получить не заархивированное хранилище файлов по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - FilePool not found")
    })
    @GetMapping("/notArchived/{id}")
    public ResponseEntity<FilePoolDto> findByIdNotArchived(@PathVariable("id") Long id) {
        FilePoolDto filePoolDto = repository.findByIdNotArchived(id);

        if (filePoolDto == null) {
            log.log(Level.WARNING, "not found not archived FilePoolDto with id = {0}", id);
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
        log.log(Level.INFO, "not archived FilePoolDto find: id = {0}", id);
        return new ResponseEntity<>(filePoolDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список не заархивированных хранилищ файлов по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - FilePool not found")
    })
    @PostMapping("/notArchived")
    public ResponseEntity<List<FilePoolDto>> findAllByIdNotArchived(@RequestBody List<Long> ids) {
        List<FilePoolDto> filePoolDto = repository.findAllByIdNotArchived(ids);

        if (filePoolDto == null || filePoolDto.isEmpty()) {
            log.log(Level.WARNING, "List of not archived FilePoolDto not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "List of not archived FilePoolDto find");
        return new ResponseEntity<>(filePoolDto, HttpStatus.OK);
    }

}
