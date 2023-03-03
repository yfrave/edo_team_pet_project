package com.education.controller;

import com.education.service.fileConvertion.FileConversionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/service/file")
@AllArgsConstructor
@Log4j2
@ApiModel("Контроллер сервиса для конвертации файла")
public class FileConversionController {

    private FileConversionService fileConversionService;

    @ApiOperation("Сконвертировать в pdf полученный из edo-rest файл")
    @ApiResponse(code = 201, message = "Файл успешно сконвертирован в pdf")
    @PostMapping()
    public ResponseEntity<String> convertFile(String extension) {
        log.info("Получен запрос конвертацию файла");
        fileConversionService.convertFile(extension);
        log.info("Файл успешно сконвертирован");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
