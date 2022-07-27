package com.springsecurityassess1.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationResource {

    @GetMapping("/")
    public String getIndex(){
        return "<h1>Hello World!</h1>";
    }

    @GetMapping("/user")
    public String getIndexUser(){
        return "<h1>Hello World User!</h1>";
    }

    @GetMapping("/admin")
    public String getIndexAdmin(){
        return "<h1>Hello World Admin!</h1>";
    }

}
