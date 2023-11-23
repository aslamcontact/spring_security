package com.aslam.mycontact.springtest.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {



    @GetMapping("/mes")
    public ResponseEntity<String> sayHellow()
    {
        return ResponseEntity.ok("hello freom aslam");
    }



}
