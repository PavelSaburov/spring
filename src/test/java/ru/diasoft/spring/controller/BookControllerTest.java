package ru.diasoft.spring.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.spring.entity.Author;
import ru.diasoft.spring.entity.Book;
import ru.diasoft.spring.entity.Genre;
import ru.diasoft.spring.service.BookService;

@WebMvcTest(BookController.class)
@DisplayName("Контроллер для книг")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Должен возвращать все книги")
    void shouldReturnAllBooks() throws Exception {
        Genre genre = new Genre(1L,"Роман");
        Author author = new Author(1L, "Пушкин","Александр","Сергеевич");

        List<Book> books = List.of(
                new Book(1L,"Book Name 1",author, genre, new ArrayList<>()),
                new Book(2L,"Book Name 2",author, genre, new ArrayList<>())
        );

        when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(books)));
    }
    @Test
    @DisplayName("Должен возвращать книгу по идентификатору")
    void shouldReturnBookById() throws Exception {
        Genre genre = new Genre(1L,"Роман");
        Author author = new Author(1L, "Пушкин","Александр","Сергеевич");
        Book book = new Book(1L,"Book Name 1",author, genre, new ArrayList<>());

        when(bookService.findById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/v1/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }

    @Test
    @DisplayName("Должен создавать книгу")
    void shouldCreateBook() throws Exception {
        Genre genre = new Genre(1L,"Роман");
        Author author = new Author(1L, "Пушкин","Александр","Сергеевич");
        Book book = new Book(1L,"Book Name 1",author, genre, new ArrayList<>());

        when(bookService.save(any())).thenReturn(book);

        mockMvc.perform(post("/api/v1/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }

    @Test
    @DisplayName("Должен обновлять книгу")
    void shouldUpdateBook() throws Exception {
        Genre genre = new Genre(1L,"Роман");
        Author author = new Author(1L, "Пушкин","Александр","Сергеевич");
        Book book = new Book(1L,"Book Name 1",author, genre, new ArrayList<>());

        when(bookService.update(any())).thenReturn(book);

        mockMvc.perform(put("/api/v1/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }
    @Test
    @DisplayName("Должен удалять книгу по идентификатору")
    void shouldDeleteBookById() throws Exception {
        mockMvc.perform(delete("/api/v1/book/1"))
                .andExpect(status().isOk());
        verify(bookService,times(1)).deleteById(1L);
    }
}
