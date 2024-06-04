package com.example.bookku_be.repository;

import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Memo;
import com.example.bookku_be.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewByBookTitle(String bookTitle);
}
