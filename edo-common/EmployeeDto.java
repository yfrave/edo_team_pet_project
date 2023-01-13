import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @author Степан Ритман
 */
@ApiModel("DTO для класса Employee")
@Data
public class EmployeeDto {
    @ApiModelProperty("Id работника")
    private Long id;

    @ApiModelProperty("Имя работника")
    private String firstName;

    @ApiModelProperty("Фамилия работника")
    private String lastName;

    @ApiModelProperty("Отчество работника")
    private String middleName;

    @ApiModelProperty("Адрес")
    private String address;

    @ApiModelProperty("URL-адрес фото")
    private String photoUrl;

    @ApiModelProperty("ФИО в дательном падеже")
    private String fioDative;

    @ApiModelProperty("ФИО в именительном падеже")
    private String fioNominative;

    @ApiModelProperty("ФИО в родительном падеже")
    private String fioGenitive;

    @ApiModelProperty("Внешний идентификатор")
    private String externalId;

    @ApiModelProperty("Мобильный номер телефона")
    private String phone;

    @ApiModelProperty("Рабочий номер телефона")
    private String workPhone;

    @ApiModelProperty("Дата рождения")
    private Date birthDate;

    @ApiModelProperty("Имя пользователя")
    private String username;

    @ApiModelProperty("Дата создания")
    private Date creationDate;

    @ApiModelProperty("Дата архивирования")
    private Date archivedDate;
}
