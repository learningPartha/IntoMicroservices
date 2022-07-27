package com.springsecurityassess1.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginsuccess")
    public String loginSuccess(){
        return "loginsuccess";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

}
