package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class firstController {
	@GetMapping("/hi")
	public String niceToMeetU(Model model) {
		model.addAttribute("username","kang");
		return "greetings";
	}
	@RequestMapping("/bye")
	public String bye(Model model) {
		model.addAttribute("username","kang");
		return "bye";
	}
}
