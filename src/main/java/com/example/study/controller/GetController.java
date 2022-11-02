package com.example.study.controller;

//스프링부트에서 getmethod를 처리하는 방식
//GetMethod를 GetMapping을 통해 매칭시켜주고
//Request에 대한 parameter를 받아오고
//json 형태로 리턴시켜주는 것이 핵심

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method=RequestMethod.GET, path = "/getMethod")
    // localhost:8080/api/getMethod 라는 주소를 호출하면 사용자의 요청이 밑의 메소드로 들어옴
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name= "password") String pwd){
        String password = "bbbb";

        System.out.println("id : " +id); // log에 띄우기
        System.out.println("pwd : " +pwd);

        return id+pwd;
    }

    //localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    // 이렇게 parameter 많이 넣는 경우 객체로 받는 방법
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account" : "", "email" : "", "page" : 0}
        //json 형식으로 리턴하는 것이 목표 -> spring에 내장된 jackson 라이브러리 사용
        // SearchParam의 객체를 return하면 자동으로 json으로 변환되어서 리턴
        return searchParam;
    }



}
