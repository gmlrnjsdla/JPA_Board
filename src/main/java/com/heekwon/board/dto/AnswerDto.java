package com.heekwon.board.dto;

import java.time.LocalDateTime;

import com.heekwon.board.entity.Question;

import lombok.Data;

@Data
public class AnswerDto {

	private Integer id;
	private String content;
	private LocalDateTime createTime;
	private Question question;
	
}
