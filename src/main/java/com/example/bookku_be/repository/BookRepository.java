package com.example.bookku_be.repository;

import com.example.bookku_be.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
