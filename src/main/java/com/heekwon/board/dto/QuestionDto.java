package com.heekwon.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.heekwon.board.entity.Answer;

import lombok.Data;

@Data
public class QuestionDto {

	private Integer id;
	private String subject;
	private String content;
	private LocalDateTime createTime;
	private List<Answer> answerList;
	
}
