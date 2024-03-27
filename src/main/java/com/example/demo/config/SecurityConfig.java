package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

                http.addFilter(corsConfig.corsFilter());
        http.csrf(AbstractHttpConfigurer::disable).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN","MANAGER","USER")
                                .requestMatchers("/api/v1/manager/**").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers("/api/v1/admin/**").hasAnyRole("ADMIN")
                                .anyRequest().permitAll()
                );

        return http.build();
    }

}
