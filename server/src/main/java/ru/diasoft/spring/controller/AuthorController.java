package ru.diasoft.spring.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.entity.Author;
import ru.diasoft.spring.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping(value = "/api/v1/authors")
    public List<Author> findAuthors(){
        return authorService.findAll();
    }

    @GetMapping(value = "/api/v1/author/{id}")
    public Author findAuthorById(@PathVariable Long id){
        return authorService.findById(id).get();
    }
}
