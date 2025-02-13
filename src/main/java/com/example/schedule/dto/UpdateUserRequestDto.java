package com.example.schedule.dto;

import lombok.Getter;

//사용자 정보 수정 요청 DTO
@Getter
public class UpdateUserRequestDto {

    private String name; //변경할 사용자 이름
    private String email; //변경할 이메일

    public UpdateUserRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
