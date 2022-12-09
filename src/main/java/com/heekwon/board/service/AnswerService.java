package com.heekwon.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.heekwon.board.dto.AnswerDto;
import com.heekwon.board.entity.Answer;
import com.heekwon.board.entity.Question;
import com.heekwon.board.repository.AnswerRepository;
import com.heekwon.board.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	public void answerCreate(Integer id, String content) {
		
		Optional<Question> oq = questionRepository.findById(id);
		Question q = oq.get();
		Answer a = new Answer();
		a.setContent(content);
		a.setQuestion(q);
		
		answerRepository.save(a);
		
	}
	
}
