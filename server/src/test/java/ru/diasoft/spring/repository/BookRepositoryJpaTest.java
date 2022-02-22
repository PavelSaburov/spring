package ru.diasoft.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.diasoft.spring.entity.Author;
import ru.diasoft.spring.entity.Book;
import ru.diasoft.spring.entity.Genre;

@DataJpaTest
@DisplayName("Репозиторий для книг")
public class BookRepositoryJpaTest {
    private static final int BOOK_COUNT = 3;
    private static final String BOOK_NAME = "test";
    private static final long AUTHOR_ID = 1;
    private static final long GENRE_ID = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private Book book;

    @Autowired
    private BookRepository bookRepository;

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
    }

    @Test
    @DisplayName("должен возвращать количество книг")
    void shouldReturnBookCount() {
        long actualBookCount = bookRepository.count();
        assertThat(actualBookCount).isEqualTo(BOOK_COUNT);
    }

    @Test
    @DisplayName("должен добавлять книгу")
    void shouldInsertBook() {
        Book returnedBook = bookRepository.save(book);
        assertThat(bookRepository.findById(returnedBook.getBookId())).isNotNull();
    }

    @Test
    @DisplayName("должен обновлять книгу")
    void shouldUpdateBook() {
        Book actualBook = bookRepository.findById(EXISTING_BOOK_ID).get();
        actualBook.setBookName(BOOK_NAME);
        actualBook.getAuthor().setAuthorId(AUTHOR_ID);
        actualBook.getGenre().setGenreId(GENRE_ID);
        Book returnedBook = bookRepository.save(actualBook);
        assertThat(returnedBook.getBookName()).isEqualTo(BOOK_NAME);
        assertThat(returnedBook.getAuthor().getAuthorId()).isEqualTo(AUTHOR_ID);
        assertThat(returnedBook.getGenre().getGenreId()).isEqualTo(GENRE_ID);
    }

    @Test
    @DisplayName("должен возвращать книгу по идентификатору")
    void shouldFindByIdBook() {
        Book actualBook = bookRepository.save(book);
        Optional<Book> returnedBook = bookRepository.findById(actualBook.getBookId());
        assertThat(returnedBook.get().getBookName()).isEqualTo(BOOK_NAME);
        assertThat(returnedBook.get().getAuthor().getAuthorId()).isEqualTo(AUTHOR_ID);
        assertThat(returnedBook.get().getGenre().getGenreId()).isEqualTo(GENRE_ID);
    }

    @Test
    @DisplayName("должен возвращать все книги")
    void shouldFindAllBook() {
        List<Book> actualListBook = bookRepository.findAll();
        assertThat(actualListBook.size()).isEqualTo(BOOK_COUNT);
    }

    @Test
    @DisplayName("должен удалять книгу по идентификатору")
    void shouldDeleteByIdBook() {
        long actualBookCount = bookRepository.count();
        bookRepository.deleteById(EXISTING_BOOK_ID);
        long returnedBookCount = bookRepository.count();
        assertThat(actualBookCount - 1).isEqualTo(returnedBookCount);
    }
}
