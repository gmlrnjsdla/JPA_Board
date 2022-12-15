package com.heekwon.board.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;					//질문게시판 번호
	
	@Column(length = 100)
	private String subject;				//질문게시판 제목
	
	@Column(length = 1000)
	private String content; 			//질문게시판 내용
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime;	//글 등록일시
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteMember writer; // 글쓴이
	
	private LocalDateTime modifyDate;
	
}
