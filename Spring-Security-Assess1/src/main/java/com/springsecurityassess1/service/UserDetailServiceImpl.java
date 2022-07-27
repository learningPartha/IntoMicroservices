package com.springsecurityassess1.service;

import com.springsecurityassess1.model.MyUserDetails;
import com.springsecurityassess1.model.User;
import com.springsecurityassess1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    public static final int USERS_PER_PAGE = 5;
    public static final int MAX_FAILED_ATTEMPT = 3;

    @Autowired
    private UserRepository userRepository;

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
        int newFailedAttempt = user.isFailedAttempt()+1;
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
