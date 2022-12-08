package com.heekwon.board.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;		//답변게시판 번호
	
	@Column(length = 1000)
	private String content;	//답변게시판 내용
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime; 
	
	@ManyToOne
	private Question question; //질문게시판 객체(질문게시판의 id 가져오기)
	
	@OneToMany(mappedBy = "questionBoard", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
}
