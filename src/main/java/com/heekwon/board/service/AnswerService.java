package com.heekwon.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.heekwon.board.entity.Answer;
import com.heekwon.board.entity.Question;
import com.heekwon.board.entity.SiteMember;
import com.heekwon.board.exception.DataNotFoundException;
import com.heekwon.board.repository.AnswerRepository;
import com.heekwon.board.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	private final MemberService memberService;
	
	public void answerCreate(Integer id, String content, String username) {
		
		Optional<Question> oq = questionRepository.findById(id);
		Question q = oq.get();
		
		SiteMember siteMember = memberService.getMemberInfo(username);
		
		Answer a = new Answer();
		a.setContent(content);
		a.setQuestion(q);
		a.setWriter(siteMember);
		
		answerRepository.save(a);
		
	}
	
	public Answer getAnswer(Integer id) {
		
		Optional<Answer> optAnswer = answerRepository.findById(id);
		
		if(optAnswer.isPresent()) {
			Answer a = optAnswer.get();
			
			return a;
		}else {
			throw new DataNotFoundException("해당답변이 없습니다.");
		}
		
	}
	
	public void answerModify(String content, Answer answer) {
		
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		
		answerRepository.save(answer);
	}

	public void delete(Integer id) {
		answerRepository.deleteById(id);
	}
	
	
	
}
