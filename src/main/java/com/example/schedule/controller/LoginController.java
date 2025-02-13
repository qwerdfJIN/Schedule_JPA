package com.example.schedule.controller;

import com.example.schedule.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//로그인 및 로그아웃 컨트롤러
@RestController
@RequestMapping("/auth")  //로그인 관련 엔드포인트
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request,
            HttpServletResponse response) {

        //사용자 인증 (이메일 + 비밀번호 검증)
        boolean isValidUser = userService.validateUser(email, password);

        if (!isValidUser) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }

        //세션 생성 (로그인 유지)
        HttpSession session = request.getSession();
        session.setAttribute("USER", email);  //세션에 사용자 정보 저장

        return ResponseEntity.ok("Login successful.");
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션 삭제
        }
        return ResponseEntity.ok("Logout successful.");
    }
}
