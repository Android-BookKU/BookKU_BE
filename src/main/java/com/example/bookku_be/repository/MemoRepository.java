package com.example.bookku_be.repository;

import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findMemoByBook(Book book);
}
