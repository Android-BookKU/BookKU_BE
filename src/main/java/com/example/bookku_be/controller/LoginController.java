package com.example.bookku_be.controller;

import com.example.bookku_be.dto.ReqDto.LoginReqDto;
import com.example.bookku_be.dto.ReqDto.SignReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public GlobalResDto<?> SignUp(@RequestBody SignReqDto signReqDto) {

        return loginService.signUp(signReqDto);
    }

    @PostMapping("/login")
    public GlobalResDto<?> Login(@RequestBody LoginReqDto loginReqDto, HttpServletResponse response) {

        return loginService.login(loginReqDto, response);
    }
}

