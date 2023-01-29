package com.education.service.question.impl;

import com.education.entity.Question;
import com.education.model.dto.QuestionDto;
import com.education.repository.QuestionRepository;
import com.education.service.question.QuestionService;
import com.education.util.QuestionDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Класс QuestionServiceImpl.
 * Реализует QuestionService.
 * Сервис класс для бизнес-логики над Question
 */
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    /**
     * Репозиторий для работы с БД
     */
    private final QuestionRepository questionRepository;
    /**
     * Конвертер ДТО в Энтити и наоборот
     */
    private final QuestionDtoConverter converter;

    /**
     * Метод для сохранения объекта Question в БД.
     * @param question
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(QuestionDto question) {
        questionRepository.saveAndFlush(converter.toEntity(question));
    }

    /**
     * Метод для архивирования обращения Question
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        questionRepository.moveToArchive(id);
    }

    /**
     * Метод для поиска обращения Question по id.
     * @param id
     * @return QuestionDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public QuestionDto findById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.map(converter::toDto).orElse(null);
    }

    /**
     * Метод поиска списка обращений Question по списку id.
     * @param ids
     * @return List<QuestionDto>
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<QuestionDto> findAllById(List<Long> ids) {
        List<Question> questions = questionRepository.findAllById(ids);
        return !questions.isEmpty()
                ? questions
                .stream()
                .map(converter::toDto)
                .toList()
                : null;
    }

    /**
     * Метод для поиска неархивированного обращения Question по id.
     * @param id
     * @return QuestionDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public QuestionDto findByIdNotArchived(Long id) {
        Question question = questionRepository.findByIdNotArchived(id);
        return question != null
                ? converter.toDto(questionRepository.findByIdNotArchived(id))
                : null;
    }

    /**
     * Метод для поиска неархивированных обращений Question по списку их id.
     * @param ids
     * @return List<QuestionDto>
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<QuestionDto> findAllByIdNotArchived(List<Long> ids) {
        List<Question> questions = questionRepository.findAllByIdNotArchived(ids);
        return questions != null && !questions.isEmpty()
                ? questions
                .stream()
                .map(converter::toDto)
                .toList()
                : null;
    }
}
