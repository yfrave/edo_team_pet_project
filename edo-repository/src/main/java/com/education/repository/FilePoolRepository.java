package com.education.repository;

import com.education.entity.FilePool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilePoolRepository extends JpaRepository<FilePool, Long> {

    @Modifying
    @Query(value = "UPDATE edo.file_pool SET archived_date = CURRENT_TIMESTAMP WHERE id = :id", nativeQuery = true)
    void moveToArchive(Long id);
}
