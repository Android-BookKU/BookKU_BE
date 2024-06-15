package com.example.bookku_be.repository;

import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByMember(Member member);
    List<Book> findByMemberOrderByCreatedAtDesc(Member member);
    @Query("SELECT b FROM Book b JOIN b.memos m WHERE b.member = :member GROUP BY b ORDER BY MAX(m.createdAt) DESC")
    List<Book> findBooksByMemberOrderByLatestMemo(Member member);
    Book findBookByBookIdAndMember(Long bookId, Member member);

}
