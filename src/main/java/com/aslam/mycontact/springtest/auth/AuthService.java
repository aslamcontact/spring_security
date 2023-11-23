package com.aslam.mycontact.springtest.auth;


import com.aslam.mycontact.springtest.dao.AppUserRepository;
import com.aslam.mycontact.springtest.dao.AppUsers;
import com.aslam.mycontact.springtest.dao.Role;
import com.aslam.mycontact.springtest.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final AppUserRepository appUserRepository;
    private  final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;


    private final  AuthenticationManager authenticationManager;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {

        var user=new AppUsers();
            user.setUserName(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);

       appUserRepository.save(user);
     var jwtToken=jwtService.generateToken(user);

        return new AuthResponse(jwtToken);
     }

    public AuthResponse authenticate(RegisterRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(),
                        request.getPassword())
        );
        var user=appUserRepository.findByusername(request.getUserName())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}
