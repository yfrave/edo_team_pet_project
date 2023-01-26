package com.education.controller;

import com.education.model.dto.NomenclatureDto;
import com.education.service.nomenclature.NomenclatureService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiModel("Nomenclature API")
@AllArgsConstructor
@RestController
@RequestMapping("api/repository/nomenclature")
public class NomenclatureRestController {
    final private NomenclatureService nomenclatureService;

    @ApiOperation(value = "Сохранить сущность в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена")
    })
    @PostMapping("/")
    public ResponseEntity<NomenclatureDto> save(@RequestBody @ApiParam("Nomenclature") NomenclatureDto nomenclature) {
        return new ResponseEntity<>(nomenclatureService.save(nomenclature), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Получить номенклатуру по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NomenclatureDto> findById(@PathVariable("id") Long id) {
        NomenclatureDto nomenclature = nomenclatureService.findById(id);
        return nomenclature != null
                ? new ResponseEntity<>(nomenclature, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Получить список номенклатур по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @PostMapping("/findAll")
    public ResponseEntity<List<NomenclatureDto>> findAllById(@RequestBody List<Long> ids) {
        List<NomenclatureDto> nomenclatures = nomenclatureService.findAllById(ids);
        return nomenclatures != null && !nomenclatures.isEmpty()
                ? new ResponseEntity<>(nomenclatures, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Получить не заархивированную номенклатуру по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @GetMapping("/notArchived/{id}")
    public ResponseEntity<NomenclatureDto> findByIdNotArchived(@PathVariable("id") Long id) {
        NomenclatureDto nomenclature = nomenclatureService.findByIdNotArchived(id);
        return nomenclature != null
                ? new ResponseEntity<>(nomenclature, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Получить список не заархивированных номенклатур по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @PostMapping("/notArchived")
    public ResponseEntity<List<NomenclatureDto>> findAllByIdNotArchived(@RequestBody List<Long> ids) {
        List<NomenclatureDto> nomenclatures = nomenclatureService.findAllByIdNotArchived(ids);
        return nomenclatures != null && !nomenclatures.isEmpty()
                ? new ResponseEntity<>(nomenclatures, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Поместить номенклатуру в архив")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved")
    })
    @PatchMapping("/archived/{id}")
    public ResponseEntity<Void> moveToArchive(@PathVariable("id") Long id) {
        nomenclatureService.moveToArchive(id);
        return ResponseEntity.ok().build();
    }
}
