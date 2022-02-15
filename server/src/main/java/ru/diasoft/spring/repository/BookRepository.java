package ru.diasoft.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.spring.entity.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

}