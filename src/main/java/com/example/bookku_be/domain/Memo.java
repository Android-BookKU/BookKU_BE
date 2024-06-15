package com.example.bookku_be.domain;

import com.example.bookku_be.dto.ReqDto.MemoReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Memo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    @Column
    private String content;

    @JoinColumn(nullable = false, name = "bookId")
    @ManyToOne
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public Memo(MemoReqDto memoReqDto, Book book, Member member) {
        this.content = memoReqDto.getContent();
        this.book = book;
        this.member = member;
    }

    public void updateMemo(MemoReqDto memoReqDto) {
        this.content = memoReqDto.getContent();
    }
}
