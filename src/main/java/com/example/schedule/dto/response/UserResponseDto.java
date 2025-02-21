package com.example.schedule.dto.response;

import com.example.schedule.entity.User;
import lombok.Getter;

//사용자 응답 DTO
@Getter
public class UserResponseDto {

    private final String name; //사용자명

    private final String email; //이메일

    public UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //Entity -> DTO 변환
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getName(), user.getEmail());
    }
}

