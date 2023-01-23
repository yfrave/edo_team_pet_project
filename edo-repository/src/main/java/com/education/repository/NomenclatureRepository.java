package com.education.repository;

import com.education.entity.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {
    @Query(value = "SELECT n FROM Nomenclature n WHERE n.id = :id AND n.archivedDate is null")
    Optional<Nomenclature> findByIdNotArchived(@Param("id") Long id);

    @Query(value = "SELECT n FROM Nomenclature n WHERE n.id in :list AND n.archivedDate is null")
    List<Nomenclature> findAllByIdNotArchived(@Param("list") Iterable<Long> list);

    @Modifying
    @Query (value = "UPDATE edo.nomenclature SET archived_date = current_timestamp WHERE id = :id", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);
}
