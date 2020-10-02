package com.valuelinku.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valuelinku.biz.domain.Hello;

@Controller
public class HelloWorldController {

	@RequestMapping(value="/biz/hello")
	@ResponseBody
	public Hello getHello(@RequestParam("name") String name
						, @RequestParam("age") String age) {
		Hello hello = new Hello();
		hello.setName(name);
		hello.setAge(age);
		return hello;
	}
}
