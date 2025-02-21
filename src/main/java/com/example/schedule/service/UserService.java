package com.example.schedule.service;

import com.example.schedule.dto.request.UpdateUserRequestDto;
import com.example.schedule.dto.response.SignUpResponseDto;
import com.example.schedule.dto.response.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
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
    @Transactional
    public boolean validateUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> BCrypt.checkpw(password, user.getPassword()))  //BCrypt를 이용해 암호화된 비밀번호 비교
                .orElse(false);
    }

    //사용자 생성 (회원가입)
    @Transactional
    public SignUpResponseDto signUp(String name, String password, String email) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt()); //비밀번호 해싱
        User user = new User(name, hashedPassword, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    //사용자 전체 조회
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id);
        }

        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getName(), findUser.getEmail());
    }

    //사용자 정보 수정
    @Transactional
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.update(updateUserRequestDto.getName(), updateUserRequestDto.getEmail());
        return UserResponseDto.toDto(findUser);
    }

    //비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!BCrypt.checkpw(oldPassword, findUser.getPassword())) { //기존 비밀번호를 해싱된 값과 비교
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findUser.updatePassword(BCrypt.hashpw(newPassword, BCrypt.gensalt())); //새로운 비밀번호 해싱 후 저장
    }

    //사용자 삭제
    @Transactional
    public void delete(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }
}