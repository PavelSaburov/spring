package ru.diasoft.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.spring.repository.CommentRepository;
import ru.diasoft.spring.entity.Comment;

@ExtendWith(MockitoExtension.class)
@DisplayName("сервис комментариев")
public class CommentServiceImplTest {
    private static final long COMMENT_COUNT = 3;
    private static final String USERNAME = "username";
    private static final String TEXT = "text";
    private static final long EXISTING_COMMENT_ID = 1;

    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("должен возвращать количество комментариев")
    void shouldReturnCommentCount() {
        when(commentRepository.count()).thenReturn(COMMENT_COUNT);
        assertThat(commentService.count()).isEqualTo(COMMENT_COUNT);
    }

    @Test
    @DisplayName("должен добавлять комментарий")
    void shouldInsertComment() {
        Comment comment = new Comment();
        comment.setUserName(USERNAME);
        comment.setText(TEXT);
        when(commentRepository.save(comment)).thenReturn(comment);
        Comment returnedComment = commentService.save(comment);
        assertThat(comment).isEqualTo(returnedComment);
    }

    @Test
    @DisplayName("должен обновлять комментарий")
    void shouldUpdateComment() {
        Comment comment = new Comment();
        comment.setUserName(USERNAME);
        comment.setText(TEXT);
        when(commentRepository.save(comment)).thenReturn(comment);
        Comment returnedComment = commentService.update(comment);
        assertThat(returnedComment.getUserName()).isEqualTo(USERNAME);
        assertThat(returnedComment.getText()).isEqualTo(TEXT);
    }

    @Test
    @DisplayName("должен возвращать комментарий по идентификатору")
    void shouldFindByIdComment() {
        Comment comment = new Comment();
        comment.setUserName(USERNAME);
        comment.setText(TEXT);
        when(commentRepository.findById(EXISTING_COMMENT_ID)).thenReturn(Optional.of(comment));
        Optional<Comment> returnedComment = commentService.findById(EXISTING_COMMENT_ID);
        assertThat(returnedComment.get().getUserName()).isEqualTo(USERNAME);
        assertThat(returnedComment.get().getText()).isEqualTo(TEXT);
    }

    @Test
    @DisplayName("должен возвращать все комментарии")
    void shouldFindAllComments() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("CommentName");
        when(commentRepository.findAll()).thenReturn(Collections.singletonList(comment));
        List<Comment> list = commentService.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("должен удалять комментарий по идентификатору")
    void shouldDeleteByIdComment() {
        when(commentRepository.count()).thenReturn(COMMENT_COUNT);
        long actualCommentCount = commentService.count();
        commentService.deleteById(EXISTING_COMMENT_ID);
        when(commentRepository.count()).thenReturn(COMMENT_COUNT-1);
        long returnedBookCount = commentService.count();
        assertThat(actualCommentCount - 1).isEqualTo(returnedBookCount);
    }
}
