package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Rest Api �� ��Ʈ�ѷ� json��ȯ
public class firstApiController {
	@GetMapping("api/hello")
	public String hello() {
		return "hello world";
	}
}
