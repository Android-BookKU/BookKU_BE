package com.example.bookku_be.controller;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.dto.ReqDto.BookReqDto;
import com.example.bookku_be.dto.ReqDto.MemoReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/memo")
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/{bookId}")
    public GlobalResDto<?> addMemo(@PathVariable Long bookId, @RequestBody MemoReqDto memoReqDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return memoService.addMemo(bookId, memoReqDto, userDetails);
    }

    @GetMapping("/{bookId}")
    public GlobalResDto<?> getAllMemo(@PathVariable Long bookId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return memoService.getAllMemo(bookId, userDetails);
    }
}
