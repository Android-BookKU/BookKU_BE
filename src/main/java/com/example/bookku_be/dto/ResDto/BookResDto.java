package com.example.bookku_be.dto.ResDto;

import com.example.bookku_be.domain.Book;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResDto {


    @Column(nullable = false)
    Long bookId;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String author;

    public BookResDto(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
    }
}
