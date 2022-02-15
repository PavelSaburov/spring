package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.repository.GenreRepository;
import ru.diasoft.spring.entity.Genre;
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Long count() {
        return genreRepository.count();
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> findById(long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteById(long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }
}
