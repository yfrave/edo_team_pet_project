package com.education.util.Mapper;

import java.util.List;
import java.util.Set;

/**
 * Base interface for mappers
 * @param <Entity>> entity class
 * @param <Dto> dto class
 * @author Dmitii Ermolenko
 */
public interface Mappable<Entity, Dto> {
    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);

    List<Dto> toDto(List<Entity> entities);

    List<Entity> toEntity(List<Dto> dtos);
    Set<Dto> toDto(Set<Entity> entities);

    Set<Entity> toEntity(Set<Dto> dtos);
}
