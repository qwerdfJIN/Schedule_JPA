package com.example.schedule.service;

import com.example.schedule.dto.SignUpResponseDto;
import com.example.schedule.dto.UpdateUserRequestDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //사용자 인증 (이메일 + 비밀번호 검증)
    public boolean validateUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password))  //비밀번호 일치 여부 확인
                .orElse(false);  //사용자 없으면 false 반환
    }

    //사용자 생성 (회원가입)
    public SignUpResponseDto signUp(String name, String password, String email) {

        User user = new User(name, password, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    //사용자 전체 조회
    public List<UserResponseDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findById(Long id) {

        //optionalUser: 널값 예외사항 안전하게 다루기 위함
        Optional<User> optionalUser = userRepository.findById(id);

        //비었는지 확인
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getName(), findUser.getEmail());
    }

    //사용자 정보 수정
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.update(updateUserRequestDto.getName(), updateUserRequestDto.getEmail());

        return UserResponseDto.toDto(findUser);
    }

    //비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        if(!findUser.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findUser.updatePassword(newPassword);
    }

    //사용자 삭제
    @Transactional
    public void delete(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }
}
