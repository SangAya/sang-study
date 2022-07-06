package com.example.ex01.controller;

import com.example.ex01.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //컨트롤러를 통해 외부요청을 받고 서비스를 통해 비즈니스 로직을 만들고 레포지토리에서 데이터를 저장하는 정형화 패턴
public class MemberController {

//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;


    //생성자에 Autowired가 있으면 memberService를 Spring이 스프링컨테이너에있는 memberService를 가져다 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
