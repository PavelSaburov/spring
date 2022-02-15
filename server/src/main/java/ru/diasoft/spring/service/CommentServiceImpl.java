package ru.diasoft.spring.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.entity.Book;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.repository.CommentRepository;
import ru.diasoft.spring.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public Long count() {
        return commentRepository.count();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment saveByBookId(Long bookId, Comment comment) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("object not found"));
        comment.setBook(book);
        commentRepository.save(comment);
        return comment;
    }
    @Override
    public List<Comment> findAllByBookId(Long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }
}
