package com.education.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilePoolDTO {
    @ApiModelProperty("ID хранилища файла")
    private UUID storageFileId;
    @ApiModelProperty("Имя хранилища файла")
    private String name;
    @ApiModelProperty("Расширение файла")
    private String extension;
    @ApiModelProperty("Размер файла")
    private int size;
    @ApiModelProperty("Количество страниц файла")
    private int pageCount;
    @ApiModelProperty("Дата загрузки файла")
    private Date uploadDate;
    @ApiModelProperty("Дата архивирования файла")
    private Date archivedDate;
    @ApiModelProperty("Автор файла")
    private EmployeeDTO creator;
}
