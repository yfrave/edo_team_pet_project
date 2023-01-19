package com.education.repository.theme;

import com.education.entity.Theme;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Modifying
    @Query("update Theme th set th.archivedDate=:date where th.id=:theme_id")
    Integer moveToArchive(@Param("theme_id")Long id, @Param("date") ZonedDateTime archivedDate);

    List<Theme> findAllByIdAndArchivedDateIsNull(Long ids);

    Theme findByIdAndArchivedDateIsNull(Long id);

}
