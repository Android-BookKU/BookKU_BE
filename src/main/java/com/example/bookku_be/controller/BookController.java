package com.example.bookku_be.controller;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.dto.ReqDto.BookReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public GlobalResDto<?> addBook(@RequestBody BookReqDto bookReqDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return bookService.addBook(bookReqDto, userDetails);
    }
}
