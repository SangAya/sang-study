package com.example.ex01.service;

import com.example.ex01.domain.Member;
import com.example.ex01.repository.MemberRepository;
import com.example.ex01.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//서비스를 쓰면 스프링이 서비스라고 인식한다.
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //new해서 생성이아니라 외부에서 넣어주도록 바꿔준다.
    //스프링으 등록할 때 멤버레포지토리까지 서비스에 주입해준다.
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

//    회원가입
    public Long join(Member member){
        validateDuplicateMember(member);//중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    //같은 이름이 있는 중복 회원x
    //밑에처럼 로직이 나올떄는 메소드로 나눠주는게 좋다.
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                //null이 아니라 값이있으면 동작. 옵셔널로 감쌌기때문에 쓸 수있다. 
                // 과거에는 if ~null 이런식으로 썼음
                // 멤버(m)에 값이 있으면
                .ifPresent(m -> {
                    //이미 존재하는 회원입니다 
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    //서비스 클래스는 비즈니스에 가까운 용어를 써야한다.
    //레포지토리는 개발스럽게 단순히 용어 선택한다.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
