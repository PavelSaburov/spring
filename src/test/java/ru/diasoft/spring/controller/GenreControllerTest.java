package ru.diasoft.spring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.spring.entity.Genre;
import ru.diasoft.spring.service.GenreService;

@WebMvcTest(GenreController.class)
@DisplayName("Контроллер для жанров")
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenreService genreService;

    @Test
    @DisplayName("Должен возвращать все жанры")
    void shouldReturnAllGenres() throws Exception {
        final List<Genre> genres = List.of(
                new Genre(1L, "Фантастика"),
                new Genre(2L, "Приключения"),
                new Genre(3L, "Детективы")
        );

        when(genreService.findAll()).thenReturn(genres);

        mockMvc.perform(get("/api/v1/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(genres)));
    }
    @Test
    @DisplayName("Должен возвращать жанр по идентификатору")
    void shouldReturnGenreById() throws Exception {
        final Genre genre = new Genre(1L, "Фантастика");

        when(genreService.findById(1L)).thenReturn(Optional.of(genre));

        mockMvc.perform(get("/api/v1/genre/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(genre)));
    }
}
