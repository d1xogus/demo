package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // restapi용 컨트롤러 json을 반환
public class firstapicontroller {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";
    }
}
