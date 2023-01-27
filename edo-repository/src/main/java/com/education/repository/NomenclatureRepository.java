package com.education.repository;

import com.education.entity.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Связь с БД с таблицей nomenclature
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {

    /**
     * Метод предоставляет не заархивированную номенклатуру по id
     *
     * @param id Long
     * @return Optional of Nomenclature
     */
    @Query(value = "SELECT n FROM Nomenclature n WHERE n.id = :id AND n.archivedDate is null")
    Optional<Nomenclature> findByIdNotArchived(@Param("id") Long id);

    /**
     * Метод предоставляет список не заархивированных номенклатур по id
     *
     * @param list List of id
     * @return List of Nomenclature
     */
    @Query(value = "SELECT n FROM Nomenclature n WHERE n.id in :list AND n.archivedDate is null")
    List<Nomenclature> findAllByIdNotArchived(@Param("list") Iterable<Long> list);

    /**
     * Метод переводит в архив номенклатуру присваивая значение даты архивации
     * @param id Long
     * @param currentTime ZonedDateTime
     */
    @Modifying
    @Query(value = "UPDATE Nomenclature n SET n.archivedDate = :currentTime WHERE n.id = :id")
    void moveToArchive(@Param("id") Long id, @Param("currentTime") ZonedDateTime currentTime);
}
