package com.springsecurityjpa.service;

import com.springsecurityjpa.model.MyUserDetails;
import com.springsecurityjpa.model.User;
import com.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //return new MyUserDetails(username);//using springs user detail with hardcoded value
       Optional<User> user= userRepository.findByUserName(userName);
       user.orElseThrow(()->new UsernameNotFoundException("Not Found: "+userName));
       return user.map(MyUserDetails::new).get();
    }
}
