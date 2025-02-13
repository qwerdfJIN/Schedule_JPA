package com.example.schedule.service;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.UpdateScheduleRequestDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//일정 관련 비즈니스 로직 처리
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    //일정 생성
    public ScheduleResponseDto Save(String title, String contents, String name) {

        User findUser = userRepository.findUserByNameOrElseThrow(name);

        Schedule schedule = new Schedule(title, contents, findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                findUser.getName()
        );
    }

    //일정 전체 조회
    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    //일정 선택 조회
    public ScheduleResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(
                findSchedule.getId(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getUser().getName()
        );
    }

    //일정 내용 수정
    public ScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto updateScheduleRequestDto) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.setTitle(updateScheduleRequestDto.getTitle());
        findSchedule.setContents(updateScheduleRequestDto.getContents());

        Schedule updatedSchedule = scheduleRepository.save(findSchedule);

        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getTitle(),
                updatedSchedule.getContents(),
                updatedSchedule.getUser().getName()
        );
    }

    //일정 삭제
    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }

}

