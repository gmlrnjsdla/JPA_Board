package com.heekwon.board.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heekwon.board.entity.SiteMember;
import com.heekwon.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteMember memberCreate(String username, String password, String email) {
		
		SiteMember member = new SiteMember();
		member.setUsername(username);

//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(password));
		
//		member.setPassword(password);
		member.setEmail(email);
		
		memberRepository.save(member);
		
		return member;
	}

}
