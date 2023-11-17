package com.aslam.mycontact.springtest.security.jwt;

import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers()
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

      return http.build();
    }

}
