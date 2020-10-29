package com.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) {
        try {
            managerBuilder.inMemoryAuthentication()
                    .withUser("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("USER");
        } catch (Exception e) {
            throw new RuntimeException("Can't configure global user", e);
        }
    }

    protected void configure(HttpSecurity httpSecurity) {
        try {
            httpSecurity.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .permitAll()
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable();
        } catch (Exception e) {
            throw new RuntimeException("Can't authorize user", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
