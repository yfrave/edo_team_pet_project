package com.education.util.Mapper;

import java.util.List;

/**
 * Base interface for mappers
 * @param <T> entity class
 * @param <E> dto class
 * @author Dmitii Ermolenko
 */
public interface Mappable<T, E> {
    E toDto(T entity);

    T toEntity(E dto);

    List<E> toDto(List<T> entities);

    List<T> toEntity(List<E> dtos);
}

