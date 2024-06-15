package com.example.bookku_be.dto.ReqDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookReqDto {

    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String author;
    @Column(nullable = false)
    String bookImageURL;
}
