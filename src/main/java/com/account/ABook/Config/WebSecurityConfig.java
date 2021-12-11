package com.account.ABook.Config;

import org.springframework.context.annotation.Configuration;

@Configuration

public class WebSecurityConfig  {
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    */

}
