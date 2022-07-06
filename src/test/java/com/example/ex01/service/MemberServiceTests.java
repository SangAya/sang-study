package com.example.ex01.service;

import com.example.ex01.domain.Member;
import com.example.ex01.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTests {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    // 테스트는 과감히 한글로 바꿔도된다.
    // 실제 동작하는 코드들은 애매하지만 테스트는 본인이 편하게 직관적으로 알아들을 수 있으며, 실제 코드에
    // 포함되지 않기 때문에 한글로 작성해도 된다.
    //given, when, then기법을 추천
    @Test
    void 회원가입(){
        //given ~주어지다
        Member member = new Member();
        member.setName("hello");

        //when ~이걸 실행했을때
        //무엇을 검증할 것인가
        Long saveId = memberService.join(member);
        
        //then ~나오는 결과
        //검증

        //만들었던 findOne에서saveId를 넘겨준다. 바로 get으로 받아준다.(단순하게하기위해)
        Member findMember = memberService.findOne(saveId).get();
        //이름 검증 , 멤버의 이름이 findMember의 이름과 같은가?
       assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e =assertThrows(IllegalStateException.class, () ->memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers(){
    }

    @Test
    void  findOne(){
    }
}
