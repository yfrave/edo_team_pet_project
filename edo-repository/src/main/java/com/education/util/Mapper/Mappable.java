package com.education.util.Mapper;

public interface Mappable<T, E> {
    E toDto(T entity);

    T toEntity(E dto);
}
