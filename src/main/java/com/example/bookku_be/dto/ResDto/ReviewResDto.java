package com.example.bookku_be.dto.ResDto;

import com.example.bookku_be.domain.Memo;
import com.example.bookku_be.domain.Review;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDto {


    @Column(nullable = false)
    Long reviewId;
    @Column
    private String content;
    @Column
    private String star;

    public ReviewResDto(Review review) {
        this.reviewId = review.getReviewId();
        this.content = review.getContent();
        this.star = review.getStar();
    }
}
