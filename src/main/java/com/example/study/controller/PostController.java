package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
// 만약 주소가 겹치는 게 발생하면 작동하지 않는다라고 함 -> error 발생
// 이것은 Method의 단위로 Class 단위로는 겹쳐도 스프링부트가 실행되는 것에는 문제가 없으므로 똑같이 /api로 맵핑
public class PostController {

    //HTML <Form> , ajax <검색> 이런 경우에 POST를 사용
    //아래와 같이 json , xml, multipart-form / text-plain 등 어떠한 방식으로 받겠다 지정 가능
    //@PostMapping(value = "/postMethod" , produces = {"application-json"})

    //@RequestMapping(method = RequestMethod.POST, path = '/postMethod') 이렇게 쓰면 아래랑 똑같음
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){ //HTTP post body에 data를 집어넣어서 보내겠다
        return searchParam;
    }

}
