package dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@ApiModel("Класс NomenclatureDTO - DTO для Nomenclature.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NomenclatureDTO {
    @ApiModelProperty("Дата создания номенклатуры")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата перевода в архив")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Шаблон")
    private String template;

    @ApiModelProperty("Текущее значение")
    private Long currentValue;

    @ApiModelProperty("Индекс")
    private String index;
}
