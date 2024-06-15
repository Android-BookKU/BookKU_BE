package com.example.bookku_be.controller;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.service.BookService;
import com.example.bookku_be.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/books")
    public GlobalResDto<?> getRecentBook(@AuthenticationPrincipal UserDetailsImpl userDetails){

        return homeService.getRecentBook(userDetails);
    }

    @GetMapping("/books/memo")
    public GlobalResDto<?> getBooksOrderByLatestMemo(@AuthenticationPrincipal UserDetailsImpl userDetails){

        return homeService.getBooksOrderByLatestMemo(userDetails);
    }
}
