package ru.diasoft.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.spring.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
