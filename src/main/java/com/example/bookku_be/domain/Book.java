package com.example.bookku_be.domain;

import com.example.bookku_be.dto.ReqDto.BookReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Book extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public Book(BookReqDto bookReqDto, Member member) {
        this.title = bookReqDto.getTitle();
        this.author = bookReqDto.getAuthor();
        this.member = member;
    }
}
