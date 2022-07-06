package com.example.ex01.domain;

public class Member {
    private Long id;  //이메일값, 고객이 정하는 아이디가 아니라 데이터를 구분하기 위해 시스템이 저장하는 id
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
