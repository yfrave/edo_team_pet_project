package com.education.repository.theme;

import com.education.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("select th from Theme th where th.id in (:ids) and th.archivedDate is null")
    List<Theme> findAllNotArchived(@Param("ids") List<Long> ids);

    Theme findByIdAndArchivedDateIsNull(Long id);

    @Modifying
    @Query(value = "UPDATE edo.theme SET archived_date = current_timestamp WHERE id = :theme_id", nativeQuery = true)
    Integer moveToArchive(@Param("theme_id") Long id);
}