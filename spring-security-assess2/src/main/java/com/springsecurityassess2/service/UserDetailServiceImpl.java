package com.springsecurityassess2.service;

import com.springsecurityassess2.model.MyUserDetails;
import com.springsecurityassess2.model.User;
import com.springsecurityassess2.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    public static final int USERS_PER_PAGE = 5;
    public static final int MAX_FAILED_ATTEMPT = 3;
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    @Autowired
    private UserRespository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }

    //count failed attempt
    public void increaseFailedAttempt(User user){
        int newFailedAttempt = user.getFailedAttempt()+1;
        userRepository.updateFailedAttempt(newFailedAttempt, user.getUsername());
    }

    //reset failed attempt
    public void resetFailedAttempt(String username){
        userRepository.updateFailedAttempt(0,username);
    }

    //lock account
    public void lock(User user){
        user.setAccountUnlocked(false);
        userRepository.save(user);
    }

}
