package com.heekwon.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.heekwon.board.dto.AnswerDto;
import com.heekwon.board.dto.QuestionDto;
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
	
	public List<QuestionDto> getQuestionList() {
		
		List<Question> lq = questionRepository.findAll();
		List<QuestionDto> qdtos = new ArrayList<QuestionDto>();
		
		for(int i=0; i<lq.size(); i++) {
			Question q = lq.get(i);
		
			QuestionDto qdto = new QuestionDto();
			
			qdto.setId(q.getId());
			qdto.setContent(q.getContent());
			qdto.setSubject(q.getSubject());
			qdto.setCreateTime(q.getCreateTime());
			qdto.setAnswerList(q.getAnswerList());
			
			qdtos.add(qdto);
		}
		
		return qdtos;
	}
	
	public QuestionDto getQuestionView(Integer id) {
		
		Optional<Question> oq = questionRepository.findById(id);
		QuestionDto qdto = new QuestionDto();
		
		if(oq.isPresent()) {
			Question q = oq.get();
			qdto.setId(q.getId());
			qdto.setContent(q.getContent());
			qdto.setSubject(q.getSubject());
			qdto.setCreateTime(q.getCreateTime());
			qdto.setAnswerList(q.getAnswerList());
			return qdto;
		}else {
			throw new DataNotFoundException("해당질문이 없습니다.");
		}
		
	}
	
	
	
}
