package com.example.schedule.dto.response;

import com.example.schedule.entity.Schedule;
import lombok.Getter;

//일정 응답 DTO
@Getter
public class ScheduleResponseDto {

    private final Long id; //일정 ID

    private final String title; //제목

    private final String contents; //내용

    private final String name; //작성자명

    public ScheduleResponseDto(Long id, String title, String contents, String name) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
    }

    //Entity -> Dto 변환
    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUser().getName()
        );
    }
}
