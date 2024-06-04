package com.example.bookku_be.service;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Review;
import com.example.bookku_be.dto.ReqDto.ReviewReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.dto.ResDto.ReviewResDto;
import com.example.bookku_be.exception.CustomException;
import com.example.bookku_be.exception.ErrorCode;
import com.example.bookku_be.repository.BookRepository;
import com.example.bookku_be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public GlobalResDto<?> addReview(Long bookId, ReviewReqDto reviewReqDto, UserDetailsImpl userDetails) {

        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new CustomException(ErrorCode.NOT_FOUND_BOOK)
        );

        Review review = new Review(reviewReqDto, book, userDetails.getMember());
        reviewRepository.save(review);

        return GlobalResDto.success(null, "success create review");
    }

    @Transactional
    public GlobalResDto<?> getAllReview(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new CustomException(ErrorCode.NOT_FOUND_BOOK)
        );

        List<Review> reviewList = reviewRepository.findReviewByBookTitle(book.getTitle());

        List<ReviewResDto> reviewResDtoList = reviewList.stream()
                .map(ReviewResDto::new).toList();

        return GlobalResDto.success(reviewResDtoList, "success read reviews");
    }
}
