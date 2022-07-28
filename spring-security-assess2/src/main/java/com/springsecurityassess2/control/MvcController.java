package com.springsecurityassess2.control;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/loginsuccess").setViewName("loginsuccess");
        registry.addViewController("/loginsuccessuser").setViewName("loginsuccessuser");
        registry.addViewController("/loginsuccessadmin").setViewName("loginsuccessadmin");
        registry.addViewController("/login").setViewName("login");
    }
}
