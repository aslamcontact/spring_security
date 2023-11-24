package com.aslam.mycontact.springtest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController

public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping("/register")
    public ResponseEntity<AuthResponse>  registerUser(
                  @RequestBody RegisterRequest request)
    {

        System.out.println(request.getEmail());

        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse>  authUser(
            @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
