package com.heekwon.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heekwon.board.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{

}
