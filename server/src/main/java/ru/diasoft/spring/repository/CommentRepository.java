package ru.diasoft.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.diasoft.spring.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from  Comment as c where c.book.bookId = :bookId")
    List<Comment> findAllByBookId(Long bookId);
}
