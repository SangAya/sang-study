package com.example.ex01.repository;

import com.example.ex01.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

//다른데서 쓸게 아니니 굳이 public으로 안해도 된다.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트를 하고나면 레포지토리를 클리어해줘야한다
    //그럴때  AfterEach를 쓴다.
    @AfterEach //메소드가 실행이 끝날 때 마다 어떤 동작을 한다. callback메소드로 보면됨
    public void afterEach(){
        repository.clearStore();
    }

    //save확인
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //옵셔널에서 값을 꺼낼뗴는 get을 사용
        Member result = repository.findById(member.getId()).get();
        //검증
        assertThat(member).isEqualTo(result);
//        System.out.println("result=" +(result ==member)); : true
//        Assertions.assertEquals(member, result);

    }

        @Test
         public void findByName(){
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            Member result = repository.findByName("spring1").get();

            assertThat(result).isEqualTo(member1);
        }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }



}
