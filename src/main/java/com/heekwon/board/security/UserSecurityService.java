package com.heekwon.board.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.heekwon.board.entity.SiteMember;
import com.heekwon.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<SiteMember> optSiteMember = memberRepository.findByUsername(username);
		
		if(optSiteMember.isEmpty()) { //가입된 아이디가 없으면 참
			throw new UsernameNotFoundException("등록되어 있지 않은 아이디입니다.");
		}
		SiteMember siteMember = optSiteMember.get();
		
		List<GrantedAuthority> authorites = new ArrayList<>();
		
		if("admin".equals(username)) {
			authorites.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			authorites.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		return new User(siteMember.getUsername(), siteMember.getPassword(), authorites);
	}

	
	
}
