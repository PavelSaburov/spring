package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import ru.diasoft.spring.entity.Comment;

public interface CommentService {
    Long count();

    Comment save(Comment comment);

    Optional<Comment> findById(long commentId);

    List<Comment> findAll();

    void deleteById(long commentId);

    Comment update(Comment comment);

    List<Comment> findAllByBookId(Long bookId);

    Comment saveByBookId(Long bookId, Comment comment);
}
