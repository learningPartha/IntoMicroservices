package com.springsecurityldap.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people") //find domain name for that user from ldap file
                .groupSearchBase("ou=groups").contextSource()//search organization unit mentioned as ou=groups
                .url("ldap://localhost:8389/dc=springframework,dc=org")//give ldap url and mention domain
                .and()
                .passwordCompare().passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");//get password attribute from ldap file
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and().formLogin();
    }


}
