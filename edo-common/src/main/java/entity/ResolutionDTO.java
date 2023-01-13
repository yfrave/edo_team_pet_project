package entity;

import lombok.*;

import java.sql.Date;
import java.util.List;

/**
 * POJO класс, содержащий информацию о датах и работниках.
 *
 * @author Dmitrii Ermolenko
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResolutionDTO extends BaseEntityDTO {


    /**
     * Возможные типы решения
     */
    private enum resolutionEnum {
        Резолюция,
        направление,
        запрос
    }

    /**
     * Дата создания резолюции
     */
    private Date creationDate;

    /**
     * Дата архивации
     */
    private Date archivedDate;

    /**
     * Дата последнего события
     */
    private Date lastActionDate;

    /**
     * Переменная отражающая тип решения
     */
    private resolutionEnum resolutionEnum;

    /**
     * Работник создавший резолюцию
     */
    private EmployeeDTO creator;

    /**
     * Работник подписывающий документ
     */
    private EmployeeDTO signer;

    /**
     * Работники выполняющие решение
     */
    private List<EmployeeDTO> executor;

    /**
     * Работник курирующий работу
     */
    private EmployeeDTO curator;
}
