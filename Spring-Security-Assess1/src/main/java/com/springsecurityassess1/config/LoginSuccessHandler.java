package com.springsecurityassess1.config;

import com.springsecurityassess1.model.MyUserDetails;
import com.springsecurityassess1.model.User;
import com.springsecurityassess1.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("Inside success handler");
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        if(userDetails.getUser().isFailedAttempt()>0){
            userDetailService.resetFailedAttempt(userDetails.getUsername());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
