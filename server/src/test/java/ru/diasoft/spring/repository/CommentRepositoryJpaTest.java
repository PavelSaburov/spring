package ru.diasoft.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.diasoft.spring.entity.Comment;

@DataJpaTest
@DisplayName("Репозиторий для комментариев")
public class CommentRepositoryJpaTest {
    private static final int COMMENT_COUNT = 4;
    private static final long EXISTING_COMMENT_ID = 1;
    private static final String USERNAME = "username";
    private static final String TEXT = "text";

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("должен возвращать количество комментариев")
    void shouldReturnCommentCount() {
        long actualCommentCount = commentRepository.count();
        assertThat(actualCommentCount).isEqualTo(COMMENT_COUNT);
    }

    @Test
    @DisplayName("должен добавлять комментарий")
    void shouldInsertComment() {
        Comment comment = new Comment();
        comment.setUserName(USERNAME);
        comment.setText(TEXT);
        Comment returnedComment = commentRepository.save(comment);
        assertThat(commentRepository.findById(returnedComment.getCommentId())).isNotNull();
    }

    @Test
    @DisplayName("должен обновлять комментарий")
    void shouldUpdateComment() {
        Comment actualComment = commentRepository.findById(EXISTING_COMMENT_ID).get();
        actualComment.setUserName(USERNAME);
        actualComment.setText(TEXT);
        Comment returnedComment = commentRepository.save(actualComment);
        assertThat(returnedComment.getUserName()).isEqualTo(USERNAME);
        assertThat(returnedComment.getText()).isEqualTo(TEXT);
    }

    @Test
    @DisplayName("должен возвращать комментарий по идентификатору")
    void shouldFindByIdComment() {
        Comment comment = new Comment();
        comment.setUserName(USERNAME);
        comment.setText(TEXT);
        Comment actualComment = commentRepository.save(comment);
        Optional<Comment> returnedComment = commentRepository.findById(actualComment.getCommentId());
        assertThat(returnedComment.get().getUserName()).isEqualTo(USERNAME);
        assertThat(returnedComment.get().getText()).isEqualTo(TEXT);
    }

    @Test
    @DisplayName("должен возвращать все комментарии")
    void shouldFindAllComments() {
        List<Comment> actualListCommets = commentRepository.findAll();
        assertThat(actualListCommets.size()).isEqualTo(COMMENT_COUNT);
    }

    @Test
    @DisplayName("должен удалять комментарий по идентификатору")
    void shouldDeleteByIdComment() {
        long actualCommentCount = commentRepository.count();
        commentRepository.deleteById(EXISTING_COMMENT_ID);
        long returnedBookCount = commentRepository.count();
        assertThat(actualCommentCount - 1).isEqualTo(returnedBookCount);
    }

}
