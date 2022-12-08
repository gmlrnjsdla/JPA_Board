package com.heekwon.board.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.heekwon.board.entity.Question;
import com.heekwon.board.repository.QuestionRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuestionTest {

	@Autowired
	private QuestionRepository questionRepository;
	
//	@Test
//	@DisplayName("Question Test")
//	public void testQuestion1() {
//		
//		Question q = new Question();
//		q.setSubject("안녕하세요");
//		q.setContent("반갑습니다");
////		q.setCreateTime(LocalDateTime.now());
//		
//		Question result = questionRepository.save(q);
//		assertEquals("안녕하세요", result.getSubject());
//		
//	}
	
	@Test
	@DisplayName("조회 테스트")
	public void testQuestion2() {
		
		List<Question> qList = questionRepository.findAll();
		assertEquals(2, qList.size());
		
		Question q0 = qList.get(0);
		assertEquals("안녕하세요", q0.getSubject()); 
		
		Optional<Question> oq2 = questionRepository.findById(2);
		if(oq2.isPresent()) {
			Question q = oq2.get();
			assertEquals(2, q.getId()); 
		}
	}
	
}
