package com.example.schedule.dto.request;

import lombok.Getter;

//일정 수정 요청 DTO
@Getter
public class UpdateScheduleRequestDto {

    private String title;    //수정할 제목
    private String contents; //수정할 내용
}

