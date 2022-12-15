package com.heekwon.board.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;		//답변게시판 번호
	
	@Column(length = 1000)
	private String content;	//답변게시판 내용
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime; 
	
	@ManyToOne
	private Question question; //질문게시판 객체(질문게시판의 id를 가져오는 필드생성)
	
	@ManyToOne
	private SiteMember writer; // 글쓴이
	
}
