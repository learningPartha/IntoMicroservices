package com.springsecurityjdbc.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String getHome() {
        return "<h1>Welcome</h1>";
    }

    @GetMapping("/user")
    public String getHomeUser() {
        return "<h1>Welcome User</h1>";
    }

    @GetMapping("/admin")
    public String getHomeAdmin() {
        return "<h1>Welcome Admin</h1>";
    }
}
