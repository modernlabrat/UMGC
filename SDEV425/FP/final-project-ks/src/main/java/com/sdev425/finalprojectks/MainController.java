package com.sdev425.finalprojectks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}