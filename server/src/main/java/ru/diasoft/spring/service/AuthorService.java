package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import ru.diasoft.spring.entity.Author;

public interface AuthorService {
    Long count();
    Author save(Author author);
    Optional<Author> findById(long authorId);
    List<Author> findAll();
    void deleteById(long authorId);
    Author update(Author author);
}
