package com.education.repository;

import com.education.entity.Question;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@ApiModel("Interface QuestionRepository")
@Repository
public interface QuestionRepository extends JpaRepository <Question, Long> {
    @Modifying
    @Query(value = "update edo.question set archived_date = current_timestamp where id = :id ", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);

    @Query("select q from Question q where q.id = :id and q.archivedDate is null")
    Question findByIdNotArchived(@Param("id") Long id);

    @Query("select q from Question q where q.id in (:ids) and q.archivedDate is null")
    List<Question> findAllByIdNotArchived(@Param("ids") List<Long> ids);

}
