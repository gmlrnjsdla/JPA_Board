package com.heekwon.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heekwon.board.entity.Question;
import com.heekwon.board.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

//	@Autowired
//	private QuestionRepository questionRepository;
//	
//	@Autowired
//	private AnswerRepository answerRepository;
	
	private final QuestionService questionService;
	
	@RequestMapping(value = "/")
	public String Home() {
		return "redirect:list";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:list";
	}	
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
//		List<Question> lq = questionRepository.findAll();
		
		List<Question> questionList = questionService.getQuestionList();
		
		model.addAttribute("list", questionList);
		
		return "questionList";
	}
	
	@RequestMapping(value = "/questionView/{id}")
	public String questionView(@PathVariable("id") Integer id, Model model) {

		Question q = questionService.getQuestionView(id);
		model.addAttribute("content", q);
		
		return "questionView";
	}
}
