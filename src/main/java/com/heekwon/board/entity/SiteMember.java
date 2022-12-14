package com.heekwon.board.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class SiteMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(unique = true) //유저네임 칼럼에 유니크속성 부여 (중복 x) 
	private String username; // 아이디
	private String password;
	private String email;
	
}
