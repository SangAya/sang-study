package com.example.ex02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //외부에서 파라미터를 받는다.
    // ?name=spring 로 전달한다.
    // 컨트롤러에서 name = spring로 바뀐다.  model key 값인 name을 꺼내서 치환해준다.
    @GetMapping("hello-mvc")
    public String hellMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //http://localhost:7777/hello-string?name=spring!!!
    //http에서 head부와 body부가 있는데 body부분에 데이터를 자신이 직접 넣어주겠다는 뜻
    //데이터를 그대로 내려준다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    //**api는 객체를 반환하는 것**
    //리스폰스바디가 붙어있으면 http응답에 그대로 이 데이터를 넣은 것이겠구나 라고 동작됨 (viewResolver 대신 HttpMessageConverter동작)
    //근데 객체이기 때문에 스프링은 객체가 오면 기본json방식 데이터를 만들어서 http응답에 반환하겠다라는 정책
    //리스폰스바디 --> hello 객체를 넘김 --> 객체의 몇가지 조건 HttpMessageConverter가 동작 (단순 문자면 StringConverter가 동작, 객체면 JsonConverter가 기본으로 동작)
    // 객체를 Json스타일로 바꾼다 --> Json은 바꾼 것을 요청한 곳(웹 브라우저, 서버 등)에 전달해준다.

    // http://localhost:7777/hello-api?name=spring!!!
    // key: value
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        //자바 빈 규약 -- 프로퍼티 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
