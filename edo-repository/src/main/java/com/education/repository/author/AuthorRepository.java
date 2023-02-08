package com.education.repository.author;

import com.education.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для сущности Автор
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
}
