package ru.diasoft.spring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.spring.entity.Author;
import ru.diasoft.spring.service.AuthorService;

@WebMvcTest(AuthorController.class)
@DisplayName("Контроллер для авторов")
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("Должен возвращать всех авторов")
    void shouldReturnAllAuthors() throws Exception {
        final List<Author> authors = List.of(
                new Author(1L, "Гоголь", "Николай", "Васильевич"),
                new Author(2L, "Толстой", "Лев", "Николаевич"),
                new Author(3L, "Пушкин", "Александр", "Сергеевич"),
                new Author(4L, "Барто", "Агния", "Львовна")
        );
        when(authorService.findAll()).thenReturn(authors);
        String expectedResult = mapper.writeValueAsString(authors);

        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    @DisplayName("Должен возвращать автора по идентификатору")
    void shouldReturnAuthorById() throws Exception {
        final Author author = new Author(1L, "Толстой", "Лев", "Николаевич");
        when(authorService.findById(1L)).thenReturn(Optional.of(author));
        mockMvc.perform(get("/api/v1/author/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(author)));
    }

}
