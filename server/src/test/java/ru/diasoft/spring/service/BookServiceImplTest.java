package ru.diasoft.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.entity.Author;
import ru.diasoft.spring.entity.Book;
import ru.diasoft.spring.entity.Genre;

@ExtendWith(MockitoExtension.class)
@DisplayName("сервис книг")
public class BookServiceImplTest {

    private static final long BOOK_COUNT = 2;
    private static final String BOOK_NAME = "test";
    private static final String CH_BOOK_NAME = "test2";
    private static final long AUTHOR_ID = 1;
    private static final long GENRE_ID = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private Book book, book2;
    private List<Book> bookList;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void init() {
        book = new Book();
        book.setBookName(BOOK_NAME);
        Author author = new Author();
        author.setAuthorId(AUTHOR_ID);
        Genre genre = new Genre();
        genre.setGenreId(GENRE_ID);
        book.setAuthor(author);
        book.setGenre(genre);

        book2 = new Book();
        book2.setBookName(CH_BOOK_NAME);
        book2.setAuthor(author);
        book2.setGenre(genre);

        bookList = new ArrayList<>(2);
        bookList.add(book);
        bookList.add(book2);

    }

    @Test
    @DisplayName("должен возвращать количество книг")
    void shouldReturnBookCount() {
        when(bookRepository.count()).thenReturn(BOOK_COUNT);
        assertThat(bookService.count()).isEqualTo(BOOK_COUNT);
    }

    @Test
    @DisplayName("должен добавлять книгу")
    void shouldInsertBook() {
        when(bookRepository.save(book)).thenReturn(book);
        Book retrurnedBook = bookService.save(book);
        assertThat(book).isEqualTo(retrurnedBook);
    }

    @Test
    @DisplayName("должен обновлять книгу")
    void shouldUpdateBook() {
        when(bookRepository.save(book)).thenReturn(book);

        Book actualBook = new Book();
        actualBook.setBookName(BOOK_NAME);
        Author author = new Author();
        author.setAuthorId(AUTHOR_ID);
        Genre genre = new Genre();
        genre.setGenreId(GENRE_ID);
        actualBook.setAuthor(author);
        actualBook.setGenre(genre);

        Book returnedBook = bookService.update(book);
        assertThat(returnedBook.getBookName()).isEqualTo(BOOK_NAME);
        assertThat(returnedBook.getAuthor().getAuthorId()).isEqualTo(AUTHOR_ID);
        assertThat(returnedBook.getGenre().getGenreId()).isEqualTo(GENRE_ID);
    }

    @Test
    @DisplayName("должен возвращать книгу по идентификатору")
    void shouldFindByIdBook() {
        Book book = new Book();
        book.setBookName("BookName");
        when(bookRepository.findById(EXISTING_BOOK_ID)).thenReturn(Optional.of(book));

        Optional<Book> optionalBook = bookService.findById(1L);
        assertThat(optionalBook).isPresent();
    }

    @Test
    @DisplayName("должен возвращать все книги")
    void shouldFindAllBook() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> actualListBook = bookService.findAll();
        assertThat(actualListBook.size()).isEqualTo(BOOK_COUNT);
    }

    @Test
    @DisplayName("должен удалять книгу по идентификатору")
    void shouldDeleteByIdBook() {
        when(bookRepository.count()).thenReturn(BOOK_COUNT);
        long actualBookCount = bookService.count();
        bookService.deleteById(EXISTING_BOOK_ID);
        when(bookRepository.count()).thenReturn(BOOK_COUNT - 1);
        long returnedBookCount = bookService.count();
        assertThat(actualBookCount - 1).isEqualTo(returnedBookCount);
    }

}
