package com.heekwon.board.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heekwon.board.dto.AnswerForm;
import com.heekwon.board.dto.MemberForm;
import com.heekwon.board.dto.QuestionDto;
import com.heekwon.board.dto.QuestionForm;
import com.heekwon.board.entity.Question;
import com.heekwon.board.service.AnswerService;
import com.heekwon.board.service.MemberService;
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
	private final AnswerService answerService;
	private final MemberService memberService;
	
	@RequestMapping(value = "/")
	public String Home() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/layout")
	public String layout() {
		return "layout";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:list";
	}	
	
	@RequestMapping(value = "/join")
	public String join(MemberForm memberForm) {
		return "joinForm";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "loginForm";
	}
	
	
	@RequestMapping(value = "/joinOk")
	public String answerCreate(@Valid MemberForm memberForm, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "joinForm";
		}
		
		try {
		String username = memberForm.getUsername();
		String password = memberForm.getPassword();
		String email = memberForm.getEmail();
		
		memberService.memberCreate(username, password, email);
		}catch(Exception e){
			e.printStackTrace();
			bindingResult.reject("joinFail","이미 등록된 아이디입니다.");
			return "joinForm";
		}
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, @RequestParam(value="pageNum", defaultValue = "0") int page) {
		
//		List<Question> lq = questionRepository.findAll();
		
//		List<QuestionDto> questionList = questionService.getQuestionList();
		
		Page<Question> paging = questionService.getList(page);
		
		model.addAttribute("paging", paging);
		
		return "questionList";
	}
	
	@RequestMapping(value = "/questionView/{id}")
	public String questionView(@PathVariable("id") Integer id, Model model, AnswerForm answerForm) {

		QuestionDto q = questionService.getQuestionView(id);
		model.addAttribute("content", q);
		
		return "questionView";
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/answerCreate/{id}")
	public String answerCreate(Principal principal,@PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Model model) {
		
		QuestionDto q = questionService.getQuestionView(id);
		
		String username = principal.getName(); //현재 로그인 중인 사용자의 아이디를 가져옴.
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("content", q);
			
			return "questionView";
		}
		String content = answerForm.getContent();
		answerService.answerCreate(id, content, username);
		
		return String.format("redirect:/questionView/%s", id);
	}
	
	@RequestMapping(value = "/questionForm")
	public String questionCreate(QuestionForm questionForm) {
		
		return "questionForm";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/questionCreateOk")
	public String createOk(Principal principal, @Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "questionForm";
		}
		
		String username = principal.getName();
		String subject = questionForm.getSubject();
		String content = questionForm.getContent();
		
		questionService.questionCreate(subject, content, username);
		
		return "redirect:list";
	}
	
	
	
	
	
	
	
	
}
