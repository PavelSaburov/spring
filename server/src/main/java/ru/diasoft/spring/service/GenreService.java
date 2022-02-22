package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import ru.diasoft.spring.entity.Genre;

public interface GenreService {
    Long count();

    Genre save(Genre genre);

    Optional<Genre> findById(long genreId);

    List<Genre> findAll();

    void deleteById(long genreId);

    Genre update(Genre genre);
}
