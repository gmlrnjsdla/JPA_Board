package com.heekwon.board.answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.heekwon.board.entity.Answer;
import com.heekwon.board.entity.Question;
import com.heekwon.board.repository.AnswerRepository;
import com.heekwon.board.repository.QuestionRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AnswerTest {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
//	@Test
//	@DisplayName("답변생성테스트")
//	public void testAnswer1() {
//		
//		Optional<Question> oq2 =  questionRepository.findById(2);
//		Question q = oq2.get();
//		
//		Answer a = new Answer();
//		a.setContent("답변글입니다.");
//		a.setQuestion(q);
//		
//		answerRepository.save(a);
//		
//	}
	
	@Test
	@DisplayName("답변조회테스트")
	public void testAnswer2() {
		
		Optional<Answer> oa3 =  answerRepository.findById(3);
		Answer a = oa3.get();
		
		assertEquals("답변글입니다.", a.getContent()); 
		
	}
	
	@Test
	@Transactional
	@DisplayName("질문에 달린 답변조회테스트")
	public void testAnswer3() {
		
		Optional<Question> oq2 =  questionRepository.findById(2);
		Question q = oq2.get();
		List<Answer> la2 = q.getAnswerList();
		assertEquals(1, la2.size());
		assertEquals("답변글입니다.", la2.get(0).getContent()); 
	}
	
}
