package ru.diasoft.spring.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.entity.Genre;
import ru.diasoft.spring.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping(value = "/api/v1/genres")
    public List<Genre> findGenres() {
        return genreService.findAll();
    }

    @GetMapping(value = "/api/v1/genre/{id}")
    public Genre findGenreById(@PathVariable Long id) {
        return genreService.findById(id).get();
    }
}
