package com.example.bookku_be.domain;

import com.example.bookku_be.dto.ReqDto.LoginReqDto;
import com.example.bookku_be.dto.ReqDto.SignReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pw;

    public Member(SignReqDto signReqDto){
        this.email = signReqDto.getEmail();
        this.pw = signReqDto.getPw();
    }
}
