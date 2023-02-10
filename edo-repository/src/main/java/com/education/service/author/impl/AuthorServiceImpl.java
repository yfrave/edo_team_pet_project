package com.education.service.author.impl;

import com.education.entity.Author;
import com.education.model.dto.AuthorDto;
import com.education.repository.author.AuthorRepository;
import com.education.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Имплементация интерфейса методов для работы в БД с сущностью Автора
 */

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    /**
     * Сохранение сущности
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public AuthorDto save(AuthorDto authorDto) {
        Author author = Author.dtoToAuthor(authorDto);
        authorRepository.save(author);
        return Author.authorToDto(author);
    }

    /**
     * Удаление сущности
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void delete(Long id) {
        authorRepository.delete(authorRepository.findById(id).get());
    }

    /**
     * Поиск сущности по id
     */
    @Override
    @Transactional(readOnly = true, rollbackFor=Exception.class)
    public Author findById(Long id) {
        return authorRepository.findById(id).get();
    }

    /**
     * Поиск сущностей по значениям их id
     */
    @Override
    @Transactional(readOnly = true, rollbackFor=Exception.class)
    public List<AuthorDto> findAllById(List<Long> ids) {
        List<Author> authorList = authorRepository.findAllById(ids);

        if (authorList.isEmpty()) {
            return null;
        }
        return authorList
                .stream()
                .map(Author::authorToDto)
                .collect(Collectors.toList());
    }
}
