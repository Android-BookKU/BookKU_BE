package com.example.bookku_be.domain;

import com.example.bookku_be.dto.ReqDto.MemoReqDto;
import com.example.bookku_be.dto.ReqDto.ReviewReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column
    private String bookTitle;
    @Column
    private String content;
    @Column
    private String star;

    @JoinColumn(nullable = false, name = "bookId")
    @ManyToOne
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public Review(ReviewReqDto reviewReqDto, Book book, Member member) {
        this.bookTitle = book.getTitle();
        this.content = reviewReqDto.getContent();
        this.star = reviewReqDto.getStar();
        this.book = book;
        this.member = member;
    }
}
