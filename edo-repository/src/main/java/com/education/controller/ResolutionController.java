package com.education.controller;

import com.education.service.resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.education.entity.Resolution;

@ApiOperation("Resolution API")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/repository/resolution")
public class ResolutionController {

    final private ResolutionService resolutionService;

    @PostMapping
    public ResponseEntity<Resolution> saveResolution(@RequestBody Resolution resolution){
        resolutionService.save(resolution);
        return new ResponseEntity<>(resolution, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Resolution> moveToArchiveResolution(@RequestBody Resolution resolution){
        resolutionService.moveToArchive(resolution);
        return new ResponseEntity<>(resolution, HttpStatus.OK);
    }
}
