package com.example.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //로컬호스트로 들어오면 밑에있는 메소드가 호출된다.
    public String home(){
        return "home";
    }
}
