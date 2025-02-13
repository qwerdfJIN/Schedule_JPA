package com.example.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

//로그인 인증 필터
@Slf4j
public class LoginFilter implements Filter {

    //인증이 필요 없는 URL 목록 (화이트 리스트)
    private static final String[] WHITE_LIST = {"/", "/users/signup", "/auth/login", "logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        //다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        //인증이 필요없는 URL이면 필터 통과
        if (!isWhiteList(requestURI)) {

            //세션이 존재하는 지 확인
            HttpSession session = httpRequest.getSession(false);

            //로그인 실패 로그
            if (session == null || session.getAttribute("USER") == null) {
                log.info("로그인이 필요합니다.");
                return;
            }

            //로그인 성공 로그
            log.info("로그인에 성공했습니다.");

        }

        // 요청이 다음 필터 또는 컨트롤러로 전달됨
        // 1. 화이트리스트 URL이면 그대로 통과
        // 2. 인증된 사용자는 요청을 계속 진행
        filterChain.doFilter(request, response);
    }

    //화이트 리스트에 포함된 URL인지 확인
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
