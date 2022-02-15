package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import ru.diasoft.spring.entity.Book;

public interface BookService {
    Long count();
    Book save(Book book);
    Optional<Book> findById(long bookId);
    List<Book> findAll();
    void deleteById(long bookId);
    Book update(Book book);
}
