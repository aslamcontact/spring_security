package com.aslam.mycontact.springtest.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull  HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException,
                                                     IOException
    {
        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userName;
          if(authHeader==null || !authHeader.startsWith("Bearer "))
            {
              filterChain.doFilter(request,response);
              return;
             }
         jwt=authHeader.substring(7);
          userName=jwtService.extractUserName(jwt);
    }



}
