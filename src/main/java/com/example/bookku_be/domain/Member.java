package com.example.bookku_be.domain;

import com.example.bookku_be.dto.ReqDto.LoginReqDto;
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

    public Member(LoginReqDto loginReqDto){
        this.email = loginReqDto.getEmail();
        this.pw = loginReqDto.getPw();
    }
}
