package ru.diasoft.spring.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.spring.entity.Book;
import ru.diasoft.spring.entity.Comment;
import ru.diasoft.spring.service.CommentService;

@WebMvcTest(CommentController.class)
@DisplayName("Контроллер для комментариев")
public class CommentControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper mapper;

        @MockBean
        private CommentService commentService;

        @Test
        @DisplayName("Должен возвращать все комментарии по книге")
        void shouldReturnAllCommentsForBookId() throws Exception {
                Book book = new Book();
                book.setBookName("Test Title");

                Comment comment = new Comment();
                comment.setUserName("anonymous");
                comment.setText("text comment");
                comment.setBook(book);

                when(commentService.findAllByBookId(any())).thenReturn(Collections.singletonList(comment));

                mockMvc.perform(get("/api/v1/book/1/comments"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(mapper.writeValueAsString(List.of(comment))));
        }

        @Test
        @DisplayName("Должен создавать комментарий по книге")
        void shouldCreateCommentForBookId() throws Exception {
                Book book = new Book();
                book.setBookName("Test Title");

                Comment comment = new Comment();
                comment.setUserName("anonymous");
                comment.setText("text comment");
                comment.setBook(book);

                when(commentService.saveByBookId(any(), any())).thenReturn(comment);

                mockMvc.perform(post("/api/v1/book/1/comment"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(mapper.writeValueAsString(comment)));
        }

}
