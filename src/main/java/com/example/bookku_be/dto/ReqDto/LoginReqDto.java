package com.example.bookku_be.dto.ReqDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pw;

    public void setEncodePwd(String encodePwd) {

        this.pw = encodePwd;
    }
}
