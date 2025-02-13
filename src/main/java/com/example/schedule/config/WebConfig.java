package com.example.schedule.config;

import com.example.schedule.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//로그인 필터 등록 설정
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); //필터 등록
        filterRegistrationBean.setOrder(1); //필터 우선 순위 설정
        filterRegistrationBean.addUrlPatterns("/*"); //모든 요청에 필터 적용

        return filterRegistrationBean;
    }

}
