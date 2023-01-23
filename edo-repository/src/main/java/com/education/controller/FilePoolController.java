package com.education.controller;

import com.education.entity.FilePool;
import com.education.service.filepool.FilePoolService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/repository/file_pool")
public class FilePoolController {
    private final FilePoolService filePoolService;

    @Autowired
    public FilePoolController(FilePoolService filePoolService) {
        this.filePoolService = filePoolService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilePool> fetchFilePool(@PathVariable("id") Long id) {
        FilePool filePool = filePoolService.findById(id);
        return filePool != null
                ? new ResponseEntity<>(filePool, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<FilePool>> fetchFindAllById(@RequestBody
                                                           @ApiParam(name = "Address list")
                                                           List<Long> ids) {
        List<FilePool> filePools = filePoolService.findAllById(ids);
        return filePools != null
                ? new ResponseEntity<>(filePools, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/")
    public ResponseEntity<FilePool> save (@RequestBody FilePool filePool){
        return new ResponseEntity<>(filePoolService.add(filePool), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> moveToArchived(@PathVariable Long id) {
        filePoolService.moveToArchive(id);
        return ResponseEntity.ok().build();
    }

}
