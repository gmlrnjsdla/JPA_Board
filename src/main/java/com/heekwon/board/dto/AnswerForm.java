package com.heekwon.board.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AnswerForm {
	
	@NotEmpty(message = "답변은 필수입력사항입니다.")
	@Size(min = 5, message = "답변은 5자 이상만 가능합니다.")
	private String content;
	
}
