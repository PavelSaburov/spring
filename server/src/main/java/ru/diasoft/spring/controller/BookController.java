package ru.diasoft.spring.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.entity.Book;
import ru.diasoft.spring.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/api/v1/books")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping(value = "/api/v1/book/{id}")
    public Book findById(@PathVariable Long id){
        return bookService.findById(id).get();
    }

    @PostMapping(value = "/api/v1/book")
    public Book save(Book book) {
        return bookService.save(book);
    }

    @PutMapping(value = "/api/v1/book/{id}")
    public Book update(@PathVariable Long id, Book book){
        return bookService.update(book);
    }

    @DeleteMapping(value = "/api/v1/book/{id}")
    public void deleteById(@PathVariable Long id){
        bookService.deleteById(id);
    }
}
