package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository repo;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        return jwtUtil.generateToken(user.getUsername());
    }

    @GetMapping("/test")
    public String test() {
        return "Public API Working!";
    }
}
