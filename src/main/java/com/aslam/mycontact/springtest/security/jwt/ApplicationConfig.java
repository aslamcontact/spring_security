package com.aslam.mycontact.springtest.security.jwt;

import com.aslam.mycontact.springtest.dao.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationConfig {

    @Autowired
    AppUserRepository appUserRepository;

    @Bean
    public UserDetailsService userDetailsService()
    {
        return username -> appUserRepository.findByUsername(username).orElseThrow(
                ()->   new UsernameNotFoundException("user not found")
        )
                ;
    }

}
