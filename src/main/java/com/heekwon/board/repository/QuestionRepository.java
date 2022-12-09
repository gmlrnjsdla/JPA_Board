package com.heekwon.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heekwon.board.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

	public List<Question> findBySubject(String subject);

	public List<Question> findBySubjectLike(String subject);
	
	public List<Question> findBySubjectOrderByIdDesc(String subject);
	
}
