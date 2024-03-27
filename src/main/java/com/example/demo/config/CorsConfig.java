package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서바가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정한다.
        config.addAllowedOrigin("*"); // 모든 ip에 대해서 응답을 허용
        config.addAllowedHeader("*"); // 모든 header에 대해서 응답을 허용
        config.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청을 허용
        source.registerCorsConfiguration("/api/**",config);

        return new CorsFilter(source); // CorsFilter에 CorsConfigurationSource를 전달하여 반환

    }
}