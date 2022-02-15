package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.repository.AuthorRepository;
import ru.diasoft.spring.entity.Author;
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Long count() {
        return authorRepository.count();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> findById(long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteById(long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public Author update(Author author) {
        return authorRepository.save(author);
    }
}
