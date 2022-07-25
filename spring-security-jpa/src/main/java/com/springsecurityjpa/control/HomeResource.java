package com.springsecurityjpa.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String getHome() {
        return "<h1>Welcome</h1>";
    }

    @GetMapping("/user")
    public String getUserHome() {
        return "<h1>WelcomeUser</h1>";
    }

    @GetMapping("/admin")
    public String getAdminHome() {
        return "<h1>WelcomeAdmin</h1>";
    }

}
