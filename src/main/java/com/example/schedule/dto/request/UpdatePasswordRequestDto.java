package com.example.schedule.dto;

import lombok.Getter;

//비밀번호 변경 요청 DTO
@Getter
public class UpdatePasswordRequestDto {

    private final String oldPassword; //기존 비밀번호

    private final String newPassword; //새 비밀번호

    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
