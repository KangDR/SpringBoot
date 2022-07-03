package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Rest Api 용 컨트롤러 json반환
public class firstApiController {
	@GetMapping("api/hello")
	public String hello() {
		return "hello world";
	}
}
