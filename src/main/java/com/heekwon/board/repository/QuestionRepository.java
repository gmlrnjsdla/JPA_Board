package com.heekwon.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heekwon.board.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

	
	
}
