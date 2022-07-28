package com.springsecurityassess2.config;

import com.springsecurityassess2.model.MyUserDetails;
import com.springsecurityassess2.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyUserDetails userDetails = (MyUserDetails) userDetailService.loadUserByUsername(username);

        if(userDetails!=null){
            System.out.println("User failed to login "+username);
            if(userDetails.isEnabled() && userDetails.isAccountNonLocked()){
                if(userDetails.getFailedAttempt() < UserDetailServiceImpl.MAX_FAILED_ATTEMPT-1){
                    userDetailService.increaseFailedAttempt(userDetails.getUser());
                    exception = new BadCredentialsException("Bad Credential attempt "+userDetails.getFailedAttempt()+1
                    +"! Max Attempt "+UserDetailServiceImpl.MAX_FAILED_ATTEMPT);
                }
                else{
                    userDetailService.lock(userDetails.getUser());
                    exception = new LockedException("User account locked. Please contact admin.");
                }
            }
        }else{
            System.out.println("Username not found");
            exception = new UsernameNotFoundException("Username not found");
        }

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
