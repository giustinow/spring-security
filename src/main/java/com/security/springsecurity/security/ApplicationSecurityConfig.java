package com.security.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.security.springsecurity.security.constants.ApplicationUserRole.ADMIN;
import static com.security.springsecurity.security.constants.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class  ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/student/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails marioRossi = User.builder()
                .username("mariorossi")
                .password(passwordEncoder().encode("password")) // Must be encoded
                .roles(STUDENT.name()) // ROLE_STUDENT -> student role
                .build();

        UserDetails giuseppeNeri = User.builder()
                .username("giuseppeneri")
                .password(passwordEncoder().encode("password"))
                .roles(ADMIN.name()) // ROLE_ADMIN -> admin role
                .build();

        return new InMemoryUserDetailsManager(
                marioRossi,
                giuseppeNeri
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
