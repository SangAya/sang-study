package com.example.ex01.repository;

import com.example.ex01.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//구현체
//@Repository //repository라고 인식
public class MemoryMemberRepository implements MemberRepository {

    //저장할 공간 (Map 사용)
    private static Map<Long, Member> store = new HashMap<>();
    //시퀀스 (0,1,2 등 key값을 생성해주는 것)
    private static Long sequence =0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //store에 넣기 전에 id값을 세팅해준다.
        store.put(member.getId(), member); //store에 저장
        return member; //저장된 결과 반환
    }

    //findById는 store에서 꺼낸다.
    @Override
    public Optional<Member> findById(Long id) {
        //null을 Optional로 감싼다. 후에 클라이언트가 어떤 것을 할 수 있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
