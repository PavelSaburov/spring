package ru.diasoft.spring.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.entity.Comment;
import ru.diasoft.spring.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping(value = "/api/v1/book/{bookId}/comments")
    public List<Comment> findAllByBookId(@PathVariable Long bookId) {
        return commentService.findAllByBookId(bookId);
    }

    @PostMapping(value = "/api/v1/book/{bookId}/comment")
    public Comment save(@PathVariable Long bookId, Comment comment) {
        return commentService.saveByBookId(bookId, comment);
    }
}
