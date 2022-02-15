package ru.diasoft.spring.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private long bookId;

    @Column(name = "BOOK_NAME", nullable = false, unique = true)
    private String bookName;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}
