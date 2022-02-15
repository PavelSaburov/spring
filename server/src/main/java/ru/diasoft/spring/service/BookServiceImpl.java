package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.entity.Book;
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Long count() {
        return bookRepository.count();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }
}
