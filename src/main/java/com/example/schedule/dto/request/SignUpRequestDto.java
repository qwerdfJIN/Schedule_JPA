package com.example.schedule.dto.request;

import lombok.Getter;

//회원가입 요청 DTO
@Getter
public class SignUpRequestDto {

    private final String name; //사용자명

    private final String email; //이메일

    private final String password; //비밀번호

    public SignUpRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
