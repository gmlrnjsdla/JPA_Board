package com.heekwon.board.question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.heekwon.board.service.QuestionService;

@SpringBootTest
public class QuestionDummyTest {

	@Autowired
	private QuestionService questionService;
	
	@Test
	public void testDummyData(){
		for(int i=0; i<150; i++) {
			String subject = String.format("testData%d", i);
			String content = "testData content";
			questionService.questionCreate(subject, content);
		}
	}
	
}
