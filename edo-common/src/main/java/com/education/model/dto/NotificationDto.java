package com.education.model.dto;

import com.education.model.enumEntity.EnumNotification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.ZonedDateTime;

@ApiModel("Класс NotificationDto, dto для класса Notification.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NotificationDto {

    @ApiModelProperty("Id оповещения")
    private Long id;

    @ApiModelProperty("Тип оповещения")
    private EnumNotification enumNotification;

    @ApiModelProperty("Дата оповещения")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Сообщение")
    private String message;

    @ApiModelProperty("Пользователь")
    private EmployeeDto employeeDto;
}
