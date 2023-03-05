package com.education.service.nomenclature.impl;

import com.education.client.NomenclatureRestTemplateClient;
import com.education.model.dto.NomenclatureDto;
import com.education.service.nomenclature.NomenclatureService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Представляет реализацию операций над номенклатурой
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

@Service
@AllArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    /**
     * Клиент для связи с модулем edo-repository
     */
    private final NomenclatureRestTemplateClient client;

    /**
     * Сохраняет номенклатуру
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        return client.save(nomenclature);
    }

    /**
     * Переводит номенклатуру в архив
     * @param id Long
     */
    @Override
    public void moveToArchive(Long id) {
        client.moveToArchive(id);
    }

    /**
     * Предоставляет NomenclatureDto номенклатуры по id
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findById(Long id) {
        return client.findById(id);
    }

    /**
     * Предоставляет список NomenclatureDto номенклатур по id
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllById(List<Long> list) {
        return client.findAllById(list);
    }

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры по id
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        return client.findByIdNotArchived(id);
    }

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> list) {
        return client.findAllByIdNotArchived(list);
    }

    @Override
    public String getNumberFromTemplate(NomenclatureDto nomenclatureDto) {
        var template = nomenclatureDto.getTemplate();
        if (template == null) {
            template = "%ЧИС%ГОД-%ЗНАЧ/2";
        }
        final String CURRENT_VALUE = nomenclatureDto.getCurrentValue().toString();
        final String YEAR = String.format("%02d", Calendar.getInstance().get(Calendar.YEAR) % 100);
        final String DAY = String.format("%02d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        return template
//  убирает больше двух знаков "%" подряд, оставляя один
                .replaceAll("%{2,}", "%")
//  заменяет число дня
                .replaceAll("%чис|%ЧИС|%число|%ЧИСЛО", DAY)
//  заменяет год
                .replaceAll("%год|%ГОД", YEAR)
//  заменяет значение
                .replaceAll("%знач|%ЗНАЧ|%значение|%ЗНАЧЕНИЕ", StringUtils.isEmpty(CURRENT_VALUE) ? "" : CURRENT_VALUE)
//  убирает проценты с флагом
                .replaceAll("%[\\W][^(а-яА-Я)]", "")
//  убирает больше двух знаков "-" подряд, оставляя один
                .replaceAll("-{2,}", "-")
//  убирает больше двух знаков "/" подряд, оставляя один
                .replaceAll("/{2,}", "/")
//  убирает знаки "-" и "/" в начале и в конце
                .replaceAll("(^[-/]+)|([-/]*$)", "");
    }
}
