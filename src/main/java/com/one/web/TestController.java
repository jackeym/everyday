package com.one.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class TestController {
	
    @RequestMapping("/webtest")
	public String hello(Locale locale, Model model) {
		return "SpringBoot Web Test!";
	}

}