package com.education.repository;

import com.education.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Modifying
    @Query(value = "UPDATE department SET archived_date = current_timestamp WHERE id = :id", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);
}
