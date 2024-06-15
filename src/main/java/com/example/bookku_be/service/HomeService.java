package com.example.bookku_be.service;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Member;
import com.example.bookku_be.dto.ResDto.BookResDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final BookRepository bookRepository;

    @Transactional
    public GlobalResDto<?> getRecentBook(UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        List<Book> bookList = bookRepository.findByMemberOrderByCreatedAtDesc(member);

        List<BookResDto> bookResDtoList = bookList.stream()
                .map(BookResDto::new).toList();

        return GlobalResDto.success(bookResDtoList, "success read books");
    }
}
