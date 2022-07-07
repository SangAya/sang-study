package com.example.ex01.controller;

import com.example.ex01.domain.Member;
import com.example.ex01.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller //컨트롤러를 통해 외부요청을 받고 서비스를 통해 비즈니스 로직을 만들고 레포지토리에서 데이터를 저장하는 정형화 패턴
public class MemberController {

//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;


    //생성자에 Autowired가 있으면 memberService를 Spring이 스프링컨테이너에있는 memberService를 가져다 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }


    //회원가입 -> members/new 이동 -> url에 직접치는건 get방식 (createForm 매핑이 되어 createForm으로 이동한다 )
    // createForm이 화면에 뿌려짐 : createForm에 있는 form이 출력된다 name이 서버로 갈때 key가 된다.
    // 등록버튼을 누르면 action url으로 post방식으로 전송된다.
    // postMapping은 보통 데이터를 전달할 때 쓴다 (get은 조회할때)
    // url은 똑같지만 post방식이 호출되어 create를 실행해준다.
    // spring이 setName을 호출하여 MemberForm에있는 name을 가져온다 == input태그의 name값과 동일
    @PostMapping("/members/new")
    public String create(MemberForm form){
//        log.info(form.toString()); 폼 들어옴확인
        // 값이 안들어올땐 위로 하나씩 생각해보며 출력해본다.
        Member member = new Member();
        //MemberForm에서 getName으로 값을 꺼냄
        member.setName(form.getName());

//        log.info("===================================");
//        log.info("들어옴1"+member.getName());
//        log.info("===================================");

        memberService.join(member);

        return "redirect:/";
    }

    //회원조회
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";
    }
}
