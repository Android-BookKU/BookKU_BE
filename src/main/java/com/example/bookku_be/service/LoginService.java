package com.example.bookku_be.service;

import com.example.bookku_be.domain.Member;
import com.example.bookku_be.domain.RefreshToken;
import com.example.bookku_be.dto.ReqDto.LoginReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.exception.CustomException;
import com.example.bookku_be.exception.ErrorCode;
import com.example.bookku_be.jwt.JwtUtil;
import com.example.bookku_be.jwt.TokenDto;
import com.example.bookku_be.repository.MemberRepository;
import com.example.bookku_be.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public GlobalResDto<?> signUp(LoginReqDto loginReqDto) {
        // 이메일 중복 확인
        if(memberRepository.findByEmail(loginReqDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_HAVE_ID);
        }

        // 비밀번호 암호화
        loginReqDto.setEncodePwd(passwordEncoder.encode(loginReqDto.getPw()));

        Member member = new Member(loginReqDto);
        memberRepository.save(member);

        return GlobalResDto.success(null, "success signup");
    }

    @Transactional
    public GlobalResDto<?> login(LoginReqDto loginReqDto, HttpServletResponse response) {
        // 계정 존재 확인
        Member member = memberRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
                );

        // 비밀번호 맞는지 확인
        if (!passwordEncoder.matches(loginReqDto.getPw(), member.getPw())) {
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        //토큰 발급
        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getEmail());
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberEmail(loginReqDto.getEmail());

        // 로그아웃한 후 로그인을 다시 하는가?
        if(refreshToken.isPresent()) {
            RefreshToken refreshToken1 = refreshToken.get().updateToken(tokenDto.getRefreshToken());
            refreshTokenRepository.save(refreshToken1);
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        //토큰을 header에 넣어서 클라이언트에게 전달하기
        setHeader(response, tokenDto);

        return GlobalResDto.success(null, "success login");
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}
