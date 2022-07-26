package com.springsecurityldap.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String getIndex(){
        return "<h1>Home Page</h1>";
    }

}
