package com.example.bookku_be.repository;

import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByMember(Member member);
}
