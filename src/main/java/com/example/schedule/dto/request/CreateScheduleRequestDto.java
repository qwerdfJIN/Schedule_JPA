package com.example.schedule.dto.request;

import lombok.Getter;

//일정 생성 요청 DTO
@Getter
public class CreateScheduleRequestDto {

    private final String title; //제목

    private final String contents; //내용

    private final String name; //작성자명

    public CreateScheduleRequestDto(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }
}
