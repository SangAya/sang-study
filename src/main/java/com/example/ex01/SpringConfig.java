package com.example.ex01;

import com.example.ex01.repository.MemberRepository;
import com.example.ex01.repository.MemoryMemberRepository;
import com.example.ex01.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
