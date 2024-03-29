package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override // override configure method for authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// set my configuration to auth object
		auth.inMemoryAuthentication().withUser("foo").password("foowd").roles("USER")
		    .and().withUser("blah").password("blahwd").roles("ADMIN");//chaining user security
	}

	@Bean // pass password encoder to spring security to encode password
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override//override method for authorization
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasAnyRole("USER","ADMIN")
				.antMatchers("/").permitAll()
				.and().formLogin();
	}



}
