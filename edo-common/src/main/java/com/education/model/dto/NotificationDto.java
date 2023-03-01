package com.education.model.dto;

import com.education.model.enumEntity.EnumNotification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("Класс NotificationDto, dto для класса Notification.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDto {

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("Тип оповещения")
    private EnumNotification enumNotification;
}
