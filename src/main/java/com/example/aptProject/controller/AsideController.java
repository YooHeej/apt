package com.example.aptProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aside")
public class AsideController {
	@Autowired
	
	@ResponseBody
	@GetMapping("index")
    public String home() {
        return "index"; // index.html 페이지로 이동
    }
}
