package com.example.security.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class WelcomeController {

//    @GetMapping("/hello")
//    public String hello(){
//        return "hello";
//    }
//
//    @GetMapping("/hi")
//    public String hi(){
//        return "hi";
//    }
    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeAdmin(){
        return ResponseEntity.ok("Welcome,Admin");
    }
}
