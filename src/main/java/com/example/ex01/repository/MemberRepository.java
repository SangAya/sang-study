package com.example.ex01.repository;

import com.example.ex01.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환된다.

    //Optional 이란 Java8에 들어간 기능
    //findById나 findByName 등을 가져올 때 값이 없으면 null일 수 있는데, 요즘에는 null일 경우
    //null을 그대로 반환하는 방법 대신 Optional을 감싸서 처리하는 방법을 많이 쓴다.
    Optional<Member> findById(Long id); //아이디로 회원찾기
    Optional<Member> findByName(String name); //이름으로 찾기
    List<Member> findAll(); //지금까지 저장된 모든 회원 리스트를 반환해준다.
}
