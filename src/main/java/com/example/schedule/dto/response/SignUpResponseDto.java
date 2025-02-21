package com.example.schedule.dto.response;

import lombok.Getter;

//회원가입 응답 DTO
@Getter
public class SignUpResponseDto {

    private final Long id; //사용자 ID

    private final String name; //사용자명

    private final String email; //이메일

    public SignUpResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
