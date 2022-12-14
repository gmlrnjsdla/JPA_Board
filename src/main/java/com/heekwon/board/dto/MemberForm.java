package com.heekwon.board.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberForm {

	@NotEmpty(message = "아이디는 필수입력사항입니다.")
	@Size(min = 5, message = "아이디는 5자 이상만 가능합니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수입력사항입니다.")
	@Size(min = 5, message = "비밀번호는 5자 이상만 가능합니다.")
	private String password;
	
	@NotEmpty(message = "이메일은 필수입력사항입니다.")
	private String email;
	
}
