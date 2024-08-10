package com.itzcorpio.spring_em_sys.controller;

import com.itzcorpio.spring_em_sys.dto.LoginRequest;
import com.itzcorpio.spring_em_sys.model.User;
import com.itzcorpio.spring_em_sys.service.AuthService;
import com.itzcorpio.spring_em_sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3175")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.verify(user));
    }

}
