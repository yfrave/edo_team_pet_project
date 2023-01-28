package com.education.repository;

import com.education.entity.FilePool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilePoolRepository extends JpaRepository<FilePool, Long> {

    /**
     * Метод переводит в архив номенклатуру присваивая значение даты архивации
     */
    @Modifying
//    @Query(value = "UPDATE FilePool f SET f.archivedDate = CURRENT_TIMESTAMP WHERE f.id = :id")
    @Query(value = "UPDATE file_pool SET archived_date = CURRENT_TIMESTAMP WHERE id = :id", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);
}

