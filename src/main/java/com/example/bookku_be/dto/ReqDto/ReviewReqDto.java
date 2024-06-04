package com.example.bookku_be.dto.ReqDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewReqDto {

    @Column(nullable = false)
    String content;
    @Column(nullable = false)
    String star;
}
