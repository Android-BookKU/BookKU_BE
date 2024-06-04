package com.example.bookku_be.service;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.domain.Book;
import com.example.bookku_be.dto.ReqDto.BookReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public GlobalResDto<?> addBook(BookReqDto bookReqDto, UserDetailsImpl userDetails) {

        Book book = new Book(bookReqDto, userDetails.getMember());
        bookRepository.save(book);

        return GlobalResDto.success(null, "success create book");
    }
}
