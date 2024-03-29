package com.springsecurityjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired//use in built H2 database
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth.jdbcAuthentication().dataSource(dataSource)//user default schema and spring user detail class
                .withDefaultSchema().withUser(
                        User.withUsername("user").password("pass").roles("USER")
                ).withUser(
                        User.withUsername("admin").password("pass").roles("ADMIN")
                );*/
        auth.jdbcAuthentication().dataSource(dataSource) //if not using default schema, you can put queries to get data
                .usersByUsernameQuery("SELECT username,password,enabled FROM USERS" +
                        "where username=?")
                .authoritiesByUsernameQuery("SELECT username,authority from AUTHORITIES" +
                        "where username=?");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
