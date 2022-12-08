package com.heekwon.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping(value = "/")
	public String Home() {
		return "index";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}	
}
