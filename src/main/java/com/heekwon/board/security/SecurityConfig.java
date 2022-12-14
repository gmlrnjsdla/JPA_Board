package com.heekwon.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration	//환경설정 파일임을 알림
@EnableWebSecurity	//모든 웹에 대한 요청이 스프링 시큐리티의 컨트롤 하에 있음을 알림
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests().requestMatchers(
				new AntPathRequestMatcher("/**")).permitAll()
			.and() 	// h2-console 제한 해제
				.csrf().ignoringRequestMatchers(
						new AntPathRequestMatcher("/h2-console/**"))
			.and()	// h2-db 표시 해제
			.headers()
			.addHeaderWriter(new XFrameOptionsHeaderWriter(
					XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
				
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
