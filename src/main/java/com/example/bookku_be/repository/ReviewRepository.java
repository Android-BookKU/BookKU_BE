package com.example.bookku_be.repository;

import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Member;
import com.example.bookku_be.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewByBookTitle(String bookTitle);
    Optional<Review> findByBookAndMember(Book book, Member member);
}
