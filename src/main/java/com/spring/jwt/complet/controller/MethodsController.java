package com.spring.jwt.complet.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
public class MethodsController {

    @GetMapping("/get")
    public String get(){
        return "Welcome Method Get";
    }
    @PostMapping("/post")
    public String post(){
        return "Welcome Method Post";
    }

    @PutMapping("/put")
    public String put(){
        return "Welcome Method Put";
    }

    @DeleteMapping("/delete")
    public String delete(){
        return "Welcome Method Delete";
    }
}
