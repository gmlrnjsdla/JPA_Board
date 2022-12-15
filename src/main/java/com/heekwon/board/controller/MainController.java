package com.heekwon.board.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.heekwon.board.dto.AnswerForm;
import com.heekwon.board.dto.MemberForm;
import com.heekwon.board.dto.QuestionForm;
import com.heekwon.board.entity.Answer;
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

		Question q = questionService.getQuestionView(id);
		model.addAttribute("content", q);
		
		return "questionView";
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/answerCreate/{id}")
	public String answerCreate(Principal principal,@PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Model model) {
		
		Question q = questionService.getQuestionView(id);
		
		String username = principal.getName(); //현재 로그인 중인 사용자의 아이디를 가져옴.
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("content", q);
			
			return "questionView";
		}
		String content = answerForm.getContent();
		answerService.answerCreate(id, content, username);
		
		return String.format("redirect:/questionView/%s", id);
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping(value = "/answerModify/{id}")
	public String answerform(Principal principal,@PathVariable("id") Integer id, AnswerForm answerForm, Model model) {
		
		Answer a = answerService.getAnswer(id);
		
		if(!principal.getName().equals(a.getWriter().getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다!");
		}
		
		answerForm.setContent(a.getContent());
		
		return "answer_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/answerModify/{id}")
	public String answerModify(Principal principal,@PathVariable("id") Integer id, @Valid AnswerForm answerForm, Model model,BindingResult bindingResult) {
		
		Answer a = answerService.getAnswer(id);
		
		if(!principal.getName().equals(a.getWriter().getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다!");
		}
		
		if(bindingResult.hasErrors()) {
			return "questionForm";
		}
		
		answerService.answerModify(answerForm.getContent(), a);
		
		return String.format("redirect:/questionView/%s", a.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping(value = "/answerDelete/{id}")
	public String answerDelete(@PathVariable("id") Integer id, Principal principal) {
		
		Answer a = answerService.getAnswer(id);
		
		if(!principal.getName().equals(a.getWriter().getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다!");
		}
		
		answerService.delete(id);
		
		return String.format("redirect:/questionView/%s", a.getQuestion().getId());
	}

	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/questionForm")
	public String questionCreate(QuestionForm questionForm) {
		
		return "questionForm";
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping(value = "/questionCreateOk")
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
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/questionCreateOk")
	public String questionCreateOk(Principal principal, @Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "questionForm";
		}
		
		String username = principal.getName();
		String subject = questionForm.getSubject();
		String content = questionForm.getContent();
		
		questionService.questionCreate(subject, content, username);
		
		return "redirect:list";
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping(value = "/modify/{id}")
	public String modify(@PathVariable("id") Integer id, QuestionForm questionForm, Principal principal) {
		
		Question q = questionService.getQuestionView(id);
		
		if(!principal.getName().equals(q.getWriter().getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다!");
		}
		
		questionForm.setSubject(q.getSubject());
		questionForm.setContent(q.getContent());
		
		
		return "questionForm";
	}
	
	@PostMapping(value = "/modify/{id}")
	public String questionModify(@PathVariable("id") Integer id, Principal principal, @Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "questionForm";
		}
		
		Question q = questionService.getQuestionView(id);
		
		questionService.modify(questionForm.getSubject(), questionForm.getContent(), q);
		
		return String.format("redirect:/questionView/%s", id);
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Principal principal) {
		
		Question q = questionService.getQuestionView(id);
		
		if(!principal.getName().equals(q.getWriter().getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다!");
		}
		
		questionService.delete(id);
		
		return "redirect:/index";
	}
	
	
	
	
}
