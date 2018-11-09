package com.dengyuanke.weatherbasic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengyuanke
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
	    return "你好";
	}

}
