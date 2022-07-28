package com.springsecurityassess2.config;

import com.springsecurityassess2.model.MyUserDetails;
import com.springsecurityassess2.service.UserDetailServiceImpl;
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
        String redirectUrl = request.getContextPath();
        System.out.println("Failed attempt"+userDetails.getUser().getFailedAttempt());
        if(userDetails.getUser().getFailedAttempt()>0){
            System.out.println("Inside reset handler"+userDetails.getUsername());
            userDetailService.resetFailedAttempt(userDetails.getUsername());
        }
        if(userDetails.getUser().getRole().equalsIgnoreCase(UserDetailServiceImpl.USER)){
            redirectUrl="loginsuccessuser";
        }
        else if(userDetails.getUser().getRole().equalsIgnoreCase(UserDetailServiceImpl.ADMIN)){
            redirectUrl="loginsuccessadmin";
        }
        else{
            redirectUrl="loginsuccess";
        }
        response.sendRedirect(redirectUrl);
        //super.onAuthenticationSuccess(request, response, authentication);
    }
}
