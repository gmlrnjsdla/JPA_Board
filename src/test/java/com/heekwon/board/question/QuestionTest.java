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
//@TestPropertySource(locations = "classpath:application-test.properties")
public class QuestionTest {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	@DisplayName("Question Test")
	public void testQuestion1() {
		
		Question q = new Question();
		q.setSubject("질문있습니다");
		q.setContent("스프링질문");
//		q.setCreateTime(LocalDateTime.now());
		
		Question result = questionRepository.save(q);
		assertEquals("안녕하세요", result.getSubject());
		
	}
	
//	@Test
//	@DisplayName("조회 테스트")
//	public void testQuestion2() {
//		
//		List<Question> qList = questionRepository.findAll();
//		assertEquals(2, qList.size());
//		
//		Question q0 = qList.get(0);
//		assertEquals("안녕하세요", q0.getSubject()); 
//		
//		Optional<Question> oq2 = questionRepository.findById(2);
//		if(oq2.isPresent()) {
//			Question q = oq2.get();
//			assertEquals(2, q.getId()); 
//		}
//	}
	
//	@Test
//	@DisplayName("조회 테스트2")
//	public void testQuestion3() {
//		
//		List<Question> qList = questionRepository.findBySubject("질문있습니다");
//		Question q0 = qList.get(0);
//		assertEquals("스프링질문", q0.getContent()); 
//	}
	
//	@Test
//	@DisplayName("조회 테스트3")
//	public void testQuestion4() {
//		
//		List<Question> qList = questionRepository.findBySubjectLike("%질문%");
//		Question q0 = qList.get(0);
//		assertEquals("스프링질문", q0.getContent()); 
//	}
	
//	@Test
//	@DisplayName("조회 테스트4")
//	public void testQuestion5() {
//		
//		List<Question> qList = questionRepository.findBySubjectOrderByIdDesc("질문있습니다");
//		Question q0 = qList.get(0);
//		assertEquals("스프링질문", q0.getContent()); 
//	}
	
//	@Test
//	@DisplayName("수정 테스트")
//	public void testQuestion6() {
//		
//		Optional<Question> oq1 = questionRepository.findById(1);
//		if(oq1.isPresent()) {
//			Question q = oq1.get();
//			q.setSubject("감사합니다");
//			
//			Question sq = questionRepository.save(q);
//			assertEquals("감사합니다", sq.getSubject());
//		}
//		
//	}
	
//	@Test
//	@DisplayName("삭제 테스트")
//	public void testQuestion7() {
//		
//		Optional<Question> oq1 = questionRepository.findById(1);
//		if(oq1.isPresent()) {
//			Question q = oq1.get();
//			questionRepository.delete(q);
//		}
//		
//	}
	
}
