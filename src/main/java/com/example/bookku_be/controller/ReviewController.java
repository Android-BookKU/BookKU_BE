package com.example.bookku_be.controller;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.dto.ReqDto.ReviewReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{bookId}")
    public GlobalResDto<?> addReview(@PathVariable Long bookId, @RequestBody ReviewReqDto reviewReqDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return reviewService.addReview(bookId, reviewReqDto, userDetails);
    }

    @GetMapping("/{bookId}")
    public GlobalResDto<?> getAllReview(@PathVariable Long bookId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return reviewService.getAllReview(bookId);
    }
}
