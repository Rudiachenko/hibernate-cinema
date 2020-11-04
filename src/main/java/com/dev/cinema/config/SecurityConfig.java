package com.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/cinema-halls").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/movie-sessions").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/movie-sessions/available")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/orders").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/orders/complete")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/shopping-carts/movie-sessions")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/shopping-carts/by-user")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/inject/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
