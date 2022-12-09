package com.heekwon.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.heekwon.board.entity.Question;
import com.heekwon.board.exception.DataNotFoundException;
import com.heekwon.board.repository.AnswerRepository;
import com.heekwon.board.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	public List<Question> getQuestionList() {
		
		List<Question> lq = questionRepository.findAll();
		
		return lq;
	}
	
	public Question getQuestionView(Integer id) {
		
		Optional<Question> oq = questionRepository.findById(id);
		
		if(oq.isPresent()) {
			Question q = oq.get();
			return q;
		}else {
			throw new DataNotFoundException("해당질문이 없습니다.");
		}
		
	}
	
}
