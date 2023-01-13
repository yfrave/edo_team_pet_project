package DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthorDto {

    @ApiModelProperty("Имя автора")
    private String firstName;

    @ApiModelProperty("Фамилия автора")
    private String lastName;

    @ApiModelProperty("Отчество автора")
    private String middleName;

    @ApiModelProperty("Адрес автора")
    private String address;

    @ApiModelProperty("СНИЛС автора")
    private String snils;

    @ApiModelProperty("Номер телефона автора")
    private String mobilePhone;

    @ApiModelProperty("Электронная почта автора")
    private String email;

    @ApiModelProperty("Рабочий статус автора")
    private String employment;

    @ApiModelProperty("ФИО автора в дательном падеже")
    private String fioDative;

    @ApiModelProperty("ФИО автора в родительном падеже")
    private String fioGenitive;

    @ApiModelProperty("ФИО автора в именительном падеже")
    private String fioNominative;
}
